import React from "react";

interface Props {
  code: string;
  setCode: (v: string) => void;
  language: string;
  setLanguage: (v: string) => void;
}

export default function EditorPanel({ code, setCode, language, setLanguage }: Props) {
  return (
    <div className="flex flex-col h-full">
      <select
        value={language}
        onChange={(e) => setLanguage(e.target.value)}
        className="border p-2 mb-2 bg-white"
      >
        <option value="java">Java</option>
        <option value="python">Python</option>
        <option value="cpp">C++</option>
      </select>
      <textarea
        className="flex-1 border p-2 font-mono text-sm rounded"
        value={code}
        onChange={(e) => setCode(e.target.value)}
        placeholder="// write your code here"
      />
    </div>
  );
}