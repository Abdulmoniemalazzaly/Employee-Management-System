CREATE TABLE departments (
  id BIGSERIAL PRIMARY KEY,
  version BIGINT,
  name VARCHAR(120) NOT NULL UNIQUE,
  description VARCHAR(500),
  status VARCHAR(30) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE employees (
  id BIGSERIAL PRIMARY KEY,
  version BIGINT,
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  hire_date DATE NOT NULL,
  salary NUMERIC(14,2) NOT NULL CHECK (salary >= 0),
  status VARCHAR(30) NOT NULL,
  job_title VARCHAR(100),
  department_id BIGINT NOT NULL REFERENCES departments(id),
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_departments_status ON departments(status);
CREATE INDEX idx_employees_department ON employees(department_id);
CREATE INDEX idx_employees_status ON employees(status);
CREATE INDEX idx_employees_hire_date ON employees(hire_date);
