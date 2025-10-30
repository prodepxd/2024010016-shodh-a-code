-- add one default user
INSERT INTO "user" (username) VALUES ('test_user');

-- insert one contest with id=1
INSERT INTO contest (id, title, description)
VALUES (1, 'AI Fundamentals Contest', 'Solve basic coding problems');

-- add two problems for that contest
INSERT INTO problem
(id, title, description, input_example, output_example, test_input, expected_output, contest_id)
VALUES
(1, 'Sum of Two Numbers', 'Given two numbers, output their sum.', '2 3', '5', '2 3', '5', 1),
(2, 'Print Hello', 'Output Hello exactly once.', '-', 'Hello', '', 'Hello', 1);