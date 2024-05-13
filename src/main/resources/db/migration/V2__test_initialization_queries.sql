CREATE TABLE test (
    test_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    test_name VARCHAR(255) NOT NULL,
    test_date DATE NOT NULL,
    test_duration VARCHAR(50) NOT NULL
);

CREATE TABLE question (
    question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    test_id BIGINT NOT NULL,
    question_text TEXT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test(test_id) ON DELETE CASCADE
);

CREATE TABLE answer (
    answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_id BIGINT NOT NULL,
    answer_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
