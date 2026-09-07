CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           question_text TEXT NOT NULL,
                           answer_word VARCHAR(50) NOT NULL
);

CREATE TABLE games (
                       id SERIAL PRIMARY KEY,
                       user_id INT REFERENCES users(id),
                       question_id INT REFERENCES questions(id),
                       status VARCHAR(20) NOT NULL, -- IN_PROGRESS, WIN, LOSE
                       masked_word VARCHAR(100) NOT NULL,
                       opened_letters VARCHAR(50) DEFAULT '',
                       attempts_left INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       finished_at TIMESTAMP
);

INSERT INTO users (login, password, role) VALUES
                                              ('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN'),
                                              ('user', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'USER');

INSERT INTO questions (question_text, answer_word) VALUES
INSERT INTO questions (question_text, answer_word) VALUES
    ('Сколько деревьев успеют спилить до конца года?', 'тысяча'),
    ('Если посадить одно дерево за каждое обещание, сколько получится леса?', 'парк'),
    ('Сколько квадратных метров асфальта нужно, чтобы Бишкек перестал быть зелёным?', 'весь'),
    ('Если все деревья убрать, где жители Бишкека будут прятаться от солнца?', 'кондиционер'),
    ('Что появится раньше: новый парк или новый торговый центр?', 'асфальт'),
    ('Сколько деревьев нужно спилить, чтобы пробок стало меньше?', 'ноль'),
    ('Если дерево росло 50 лет, а его спилили за 5 минут — это рекорд?', 'рекорд'),
    ('Какой личный рекорд мэра по количеству спиленных деревьев за один рабочий день?', 'статистика'),
    ('Если дерево мешает дороге, кто должен уступить?', 'дерево'),
    ('Сколько новых саженцев нужно посадить вместо одного большого дерева?', 'десять');