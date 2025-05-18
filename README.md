# hiberus - Backend
Prueba tecnica - Hiberus

Crear tablas y datos (pre-cargados) para los componentes identificados para la solución:
o Departamento o Área:
- Nombre de departamento
- Estado (activo o departamento)
  - Empleado:
  - Nombres
  - Apellidos
  - Edad
  - Rol
  - Salario
  - Fecha de ingreso
  - Fecha de salida
  - Estado (es empleado activo o inactivo)

- Una vez creada la estructura y datos de departamentos y empleados, crear los siguientes endpoints para resolver las siguientes
operaciones:
  - /deparment/create (método POST): crea un nuevo registro para departamentos
  - /department/delete/{deparmentId} (método POST): elimina de manera lógica un registro de departamento
  - /employee/create/{departmentId}/ (método POST): crea un nuevo registro de empleado que pertenece a un departamento
  dentro de la compañia
  - /employee/delete/{employeeId}/ (método POST): elimina de manera lógica un registro de empleado que pertenece a un
  departamento dentro de la compañia. 
  - /employee/highestSalary/ (método GET): devuelve el nombre del empleado y su salario correspondiente al más alto. En
caso de que existan valores repetidos, solamente devolver un empleado. 
  - /employee/lowerAge/ (método GET): devuelve el nombre del empleado y su edad correspondiente al más joven. En caso
de que existan valores repetidos, solamente devolver un empleado. 
  - /employee/countLastMonth/ (método GET): devuelve el número de empleados que ingresaron en el último mes, tomando
como la fecha actual hacia 1 mes atrás.

### Documentación de EndPoints autogenerada con Open API
