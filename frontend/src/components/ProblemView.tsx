import React from "react";
interface Props { problem: any; }

export default function ProblemView({ problem }: Props) {
  if (!problem) return <div className="p-4">Select a problem</div>;
  return (
    <div className="p-4 border rounded bg-white">
      <h2 className="text-lg font-semibold mb-2">{problem.title}</h2>
      <p className="mb-2 whitespace-pre-wrap">{problem.description}</p>
      <p className="text-sm text-gray-600">Input Example: {problem.inputExample}</p>
      <p className="text-sm text-gray-600">Output Example: {problem.outputExample}</p>
    </div>
  );
}