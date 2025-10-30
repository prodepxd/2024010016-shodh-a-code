import { useEffect, useState } from "react";
import axios from "axios";
import ProblemView from "../../components/ProblemView";
import EditorPanel from "../../components/EditorPanel";
import Leaderboard from "../../components/Leaderboard";
import { useRouter } from "next/router";

export default function ContestPage() {
  const router = useRouter();
  const { id } = router.query;

  const [contest, setContest] = useState<any>(null);
  const [selectedProblem, setSelectedProblem] = useState<any>(null);
  const [leaderboard, setLeaderboard] = useState<any[]>([]);
  const [code, setCode] = useState("");
  const [language, setLanguage] = useState("java");
  const [status, setStatus] = useState("");

  const backend = process.env.NEXT_PUBLIC_BACKEND_URL || "http://localhost:8080";

  useEffect(() => {
  if (id) {
    axios
      .get<any>(`${backend}/api/contests/${id}`)
      .then((r) => {
        const data: any = r.data;
        setContest(data);

        // ✅ safe check before accessing index 0
        if (data.problems && data.problems.length > 0) {
          setSelectedProblem(data.problems[0]);
        } else {
          setSelectedProblem(null);
        }
      })
      .catch((err) => {
        console.error("Failed to fetch contest:", err);
      });

    refreshLeaderboard();
  }
}, [id]);

  function refreshLeaderboard() {
  axios
    .get<any>(`${backend}/api/contests/${id}/leaderboard`)
    .then((r) => setLeaderboard(r.data as any[]));
  }

  async function handleSubmit() {
    const username = localStorage.getItem("username") || "guest";
    const res = await axios.post<any>(`${backend}/api/submissions`, {
      username,
      problemId: selectedProblem.id,
      code,
      language,
});

const data: any = res.data;
const submissionId = data.id;   // ✅ fix: cast guarantees we can access .id

setStatus("Pending...");
pollStatus(submissionId);
  }

  async function pollStatus(submissionId: number) {
    const interval = setInterval(async () => {
      const res = await axios.get<any>(`${backend}/api/submissions/${submissionId}`);
      setStatus(`${res.data.status}: ${res.data.result}`);
      if (res.data.status === "Accepted" || res.data.status === "Wrong Answer") {
        clearInterval(interval);
        refreshLeaderboard();
      }
    }, 2000);
  }

  if (!contest) return <div>Loading...</div>;

  return (
    <div className="p-4 flex flex-col gap-4">
      <h1 className="text-xl font-semibold">{contest.title}</h1>
      <div className="grid md:grid-cols-3 gap-4">
        <div className="md:col-span-2 flex flex-col gap-4">
          <div>
            <label className="font-semibold mr-2">Problems:</label>
            {contest?.problems?.length ? (
    contest.problems.map((p: any) => (
        <button key={p.id}
          className={`px-2 py-1 border rounded mr-2 ${selectedProblem?.id === p.id ? 'bg-blue-100' : ''}`}
          onClick={() => setSelectedProblem(p)}>
          {p.title}
        </button>
    ))
) : (
    <p className="text-gray-500">No problems available.</p>
)}
          </div>
          <ProblemView problem={selectedProblem}/>
          <EditorPanel code={code} setCode={setCode} language={language} setLanguage={setLanguage}/>
          <button
            onClick={handleSubmit}
            className="bg-blue-500 text-white p-2 mt-2 rounded hover:bg-blue-600"
          >
            Submit
          </button>
          <p className="text-sm text-gray-700">Status: {status}</p>
        </div>
        <Leaderboard leaderboard={leaderboard}/>
      </div>
    </div>
  );
}