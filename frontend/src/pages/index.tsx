import React, { useState } from "react";
import { useRouter } from "next/router";

export default function Home() {
  const [contestId, setContestId] = useState("");
  const [username, setUsername] = useState("");
  const router = useRouter();

  function joinContest(e: React.FormEvent) {
    e.preventDefault();
    if (!contestId || !username) return;
    localStorage.setItem("username", username);
    router.push(`/contest/${contestId}`);
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <form
        onSubmit={joinContest}
        className="p-8 bg-white rounded shadow-md flex flex-col gap-3"
      >
        <h1 className="text-xl font-semibold">Join Contest</h1>
        <input
          placeholder="Contest ID"
          value={contestId}
          onChange={(e) => setContestId(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="border p-2 rounded"
        />
        <button className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
          Join
        </button>
      </form>
    </div>
  );
}