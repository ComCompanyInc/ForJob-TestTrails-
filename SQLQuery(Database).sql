CREATE DATABASE test_database
GO

USE test_database
GO

CREATE Table person (
	id INT IDENTITY(1,1) PRIMARY KEY,
	surname NVARCHAR(100),
	[name] NVARCHAR(100),
	patronymic NVARCHAR(100),
	date_of_birth DATE
)
GO

CREATE Table education_subject (
	id INT IDENTITY(1,1) PRIMARY KEY,
	[name] NVARCHAR(100)
)
GO

CREATE Table grade (
	id INT IDENTITY(1,1) PRIMARY KEY,
	average_grade INT UNIQUE
)
GO

CREATE Table person_education_subject (
	id INT IDENTITY(1,1) PRIMARY KEY,
	education_subject_id INT NOT NULL,
	person_id INT NOT NULL,
	grade_id INT NOT NULL,
	course INT NOT NULL,

	CONSTRAINT fk_person_education_subject_education_subject FOREIGN KEY(education_subject_id)
	REFERENCES education_subject(id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION,

	CONSTRAINT fk_person_education_subject_person FOREIGN KEY(person_id)
	REFERENCES person(id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION,

	CONSTRAINT fk_person_education_subject_grade FOREIGN KEY(grade_id)
	REFERENCES grade(id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION,
)
GO


-- Заполнение бд
-- Вставка персон персон
INSERT INTO person (surname, [name], patronymic, date_of_birth) VALUES
('Иванов', 'И.', 'И.', '1989-01-02'),
('Петров', 'П.', 'П.', '1991-05-13'),
('Сидоров', 'В.', 'В.', '1990-08-14'),
('Коршунов', 'П.', 'И.', '1988-12-12'),
('Артёмов', 'П.', 'Р.', '1996-04-01'),
('Соколов', 'Д.', 'И.', '1997-02-21'),
('Внуков', 'К.', 'Е.', '1987-09-18'),
('Климов', 'А.', 'А.', '1997-07-11'),
('Алексеенко', 'И.', 'А.', '1999-05-29'),
('Дедов', 'И.', 'Л.', '1992-11-04');
GO

-- Заполнение предметов
INSERT INTO education_subject ([name]) VALUES
('Информатика'),
('Экономика');
GO

-- Заполнение оценок
INSERT INTO grade (average_grade) VALUES
(2), -- id 1
(3), -- id 2
(4), -- id 3
(5); -- id 4
GO

-- Заполнение связей
INSERT INTO person_education_subject (education_subject_id, person_id, grade_id, course) VALUES
-- Информатика subject_id 1
(1, 1, 3, 5),  -- Иванов, 4 балла
(1, 2, 2, 4),  -- Петров, 3 балла
(1, 3, 4, 4),  -- Сидоров, 5 баллов
(1, 4, 1, 5),  -- Коршунов, 2 балла
(1, 5, 1, 2),  -- Артёмов, 2 балла
(1, 6, 4, 1),  -- Соколов, 5 баллов
(1, 7, 2, 5),  -- Внуков, 3 балла
(1, 8, 3, 1),  -- Климов, 4 балла
(1, 9, 4, 1),  -- Алексеенко, 5 баллов
(1, 10, 3, 3), -- Дедов, 4 балла

-- Экономика subject_id 2
(2, 1, 1, 5),  -- Иванов 2
(2, 2, 2, 4),  -- Петров 3
(2, 3, 4, 4),  -- Сидоров 5
(2, 4, 3, 5),  -- Коршунов 4
(2, 5, 4, 2),  -- Артёмов 5
(2, 6, 2, 1),  -- Соколов 3
(2, 7, 3, 5),  -- Внуков 4
(2, 8, 3, 1),  -- Климов 4
(2, 9, 4, 1),  -- Алексеенко 5
(2, 10, 3, 3); -- Дедов, 4
GO



--Запросы для выборки данных в формате представлений
USE test_database
GO

-- №1 Показать всех рождённых в СССР (до 1992 года), имеющих оценку не ниже «3» по информатике
CREATE VIEW USSR_Students_With_Good_IT_Grades AS
SELECT p.surname, p.[name], p.patronymic, p.date_of_birth, 
       g.average_grade AS it_grade, pes.course
FROM person p
JOIN person_education_subject pes ON p.id = pes.person_id
JOIN education_subject es ON pes.education_subject_id = es.id
JOIN grade g ON pes.grade_id = g.id
WHERE es.[name] = 'Информатика'
  AND g.average_grade >= 3
  AND p.date_of_birth < '1992-01-01'
GO

-- №2 Вывести список «Топ студентов», имеющих отлично по двум дисциплинам
CREATE VIEW Top_Students_With_Excellent_In_Two_Subjects AS
SELECT p.surname, p.[name], p.patronymic
FROM person p
WHERE (
    SELECT COUNT(*)
    FROM person_education_subject pes
    JOIN education_subject es ON pes.education_subject_id = es.id
    JOIN grade g ON pes.grade_id = g.id
    WHERE pes.person_id = p.id AND g.average_grade = 5
) = 2
GO

-- №3 Вывести информацию о количестве студентов на каждом из курсов
CREATE VIEW Students_Count_By_Course AS
SELECT pes.course, COUNT(DISTINCT pes.person_id) AS students_count
FROM person_education_subject pes
GROUP BY pes.course
GO

-- №4 Список студентов, сумма цифр в дате рождения < 50
CREATE VIEW Students_With_Birthdate_Digits_Sum_Less_Than_50 AS
SELECT 
    p.surname, 
    p.[name], 
    p.patronymic, 
    p.date_of_birth,
    SUM(CAST(substring(digits, number+1, 1) AS INT)) AS digits_sum
FROM person p
CROSS JOIN (
    SELECT number FROM master.dbo.spt_values 
    WHERE type = 'P' AND number < 8
) AS nums
CROSS APPLY (
    SELECT REPLACE(CONVERT(CHAR(10), p.date_of_birth, 120), '-', '') AS digits
) AS d
GROUP BY p.surname, p.[name], p.patronymic, p.date_of_birth
HAVING SUM(CAST(substring(digits, number+1, 1) AS INT)) < 50
GO



--Запуск представлений
USE test_database
GO

-- 1
SELECT * FROM USSR_Students_With_Good_IT_Grades
GO

-- 2
SELECT * FROM Top_Students_With_Excellent_In_Two_Subjects
ORDER BY surname, [name]  -- Можно добавить сортировку при выборке
GO

-- 3
SELECT * FROM Students_Count_By_Course
ORDER BY course  -- Сортируем здесь, а не в самом представлении
GO

-- 4
SELECT * FROM Students_With_Birthdate_Digits_Sum_Less_Than_50
GO