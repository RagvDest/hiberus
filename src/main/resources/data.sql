INSERT INTO hi_departments (dp_name, dp_status) VALUES
('Sistemas', 'A'),
('Contabilidad', 'A'),
('RRHH', 'I'),
('People', 'A');

INSERT INTO hi_employees (
    em_first_name, em_last_name, em_age, em_role, em_salary,
    em_hire_date, em_termination_date, em_status, em_department_id
) VALUES
('Luis', 'Pérez', 22, 'Analista', 500.00, '2021-02-10', NULL, 'A', 1),
('María', 'Gonzalez', 25, 'Contadora', null, '2020-03-11', NULL, 'A', 1),
('Pedro', 'Gómez', 30, 'Auditor', null, '2020-03-11', '2024-05-20', 'I', 2),
('José', 'López', 20, 'Asistente', null, '2020-03-11', NULL, 'A', 2);