import React from "react";
interface Props { leaderboard: any[]; }

export default function Leaderboard({ leaderboard }: Props) {
  return (
    <div className="bg-white border rounded p-2">
      <h2 className="font-semibold mb-2 text-center">Leaderboard</h2>
      <table className="text-sm w-full">
        <thead>
          <tr className="border-b">
            <th>User</th><th>Status</th><th>Problem</th><th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          {leaderboard.map((s) => (
            <tr key={s.id} className="border-b text-center">
              <td>{s.user.username}</td>
              <td>{s.status}</td>
              <td>{s.problem.title}</td>
              <td>{new Date(s.createdAt).toLocaleTimeString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}