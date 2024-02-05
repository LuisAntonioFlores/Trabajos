use db_Libros;

select *from tbl_cat_sexo;
INSERT INTO `tbl_cat_Sexo` (`Sexo_Sexo`, `Sexo_Activo`) VALUES
('Masculino', 1),
('Femenino', 1),
('No Binario', 1),
('Otro', 1),
('Prefiero no decir', 1);

select*from tbl_cat_Estados;
INSERT INTO `tbl_cat_Estados` (`Estados_Estado`, `Estados_Activo`) VALUES
('California', 1),
('Nueva York', 1),
('Texas', 1),
('Florida', 1),
('Illinois', 1);
 select * from tbl_Cat_Municipios; 
INSERT INTO `tbl_Cat_Municipios` (`Municipio_Municipio`, `Municipio_EstadoId`, `Municipio_Activo`) VALUES
('Los Ángeles', 1, 1),
('Nueva York', 2, 1),
('Houston', 3, 1),
('Miami', 4, 1),
('Chicago', 5, 1);

INSERT INTO `tbl_ope_Direccion` (`Direccion_Calle`, `Direccion_CodigoPostal`, `Direccion_NumInterior`, `Direccion_NumExterior`, `Direccion_MunicipioId`, `Direccion_Descripcion`, `Direccion_Activo`) VALUES
('123 Calle Principal', 90001, 1, 0, 1, 'Casa', 1),
('456 Calle Roble', 10001, 0, 2, 2, 'Apt 2B', 1),
('789 Calle Arce', 77001, 3, 0, 3, 'Suite 300', 1),
('987 Calle Pino', 33101, 0, 1, 4, 'Unidad 42', 1),
('654 Calle Olmo', 60601, 2, 2, 5, 'Oficina', 1);

INSERT INTO `tbl_ope_Contacto` (`Contacto_Contacto`, `Contacto_Activo`) VALUES
('juan.perez@example.com', 1),
('ana.gomez@example.com', 1),
('soporte@empresa.com', 1),
('ventas@empresa.com', 1),
('info@organizacion.org', 1);

INSERT INTO `tbl_ope_DatosPersonales` (`DatosPersonales_Nombre`, `DatosPersonales_ApellidoPaterno`, `DatosPersonales_ApellidoMaterno`, `DatosPersonales_SexoId`, `DatosPersonales_DireccionId`, `DatosPersonales_ContactoId`, `DatosPersonales_FechaNacimiento`, `DatosPersonales_Activo`) VALUES
('Juan', 'Pérez', 'Gómez', 1, 1, 1, '1990-01-15', 1),
('Ana', 'Gómez', 'Pérez', 2, 2, 2, '1985-05-22', 1),
('Alex', 'Johnson', 'Brown', 3, 3, 3, '1982-11-10', 1),
('Emily', 'Williams', 'Taylor', 1, 4, 4, '1995-07-03', 1),
('Michael', 'Davis', 'Lee', 2, 5, 5, '1988-03-28', 1);

INSERT INTO `tbl_cat_Cargos` (`Cargo_Cargo`, `Cargo_Activo`) VALUES
('Gerente', 1),
('Representante de Ventas', 1),
('Desarrollador', 1),
('Representante de Servicio al Cliente', 1),
('Contador', 1);

INSERT INTO `tbl_ope_usuarios` (`Usuario_Usuario`, `Usuario_Contrasenia`, `Usuario_DatosPersonalesId`, `Usuario_CargoId`, `Usuario_Activo`) VALUES
('juan_perez', 'contraseña123', 1, 1, 1),
('ana_gomez', 'contraseñasegura', 2, 2, 1),
('alex_johnson', 'pass123', 3, 3, 1),
('emily_williams', 'secreto', 4, 4, 1),
('michael_davis', 'contraseñaadmin', 5, 5, 1);

INSERT INTO `tbl_cat_genero` (`Genero_Nombre`, `Genero_Activo`) VALUES
('Ficción', 1),
('No Ficción', 1),
('Ciencia Ficción', 1),
('Misterio', 1),
('Romance', 1);

INSERT INTO `tbl_cat_editorial` (`Editorial_Nombre`, `Editorial_Activo`) VALUES
('Penguin Books', 1),
('HarperCollins', 1),
('Random House', 1),
('Simon & Schuster', 1),
('Macmillan Publishers', 1);

INSERT INTO `tbl_cat_idioma` (`Idioma_Nombre`, `Idioma_Activo`) VALUES
('Inglés', 1),
('Español', 1),
('Francés', 1),
('Alemán', 1),
('Chino', 1);

INSERT INTO `tbl_ope_libro` (`Libro_Nombre`, `Libro_Costo`, `Libro_Cantidad`, `Libro_GeneroId`, `Libro_EditorialId`, `Libro_IdiomaId`) VALUES
('El Gran Gatsby', 20, 50, 1, 1, 1),
('Matar a un Ruiseñor', 18, 45, 2, 2, 2),
('1984', 25, 30, 3, 3, 3),
('El Código Da Vinci', 30, 20, 4, 4, 4),
('Orgullo y Prejuicio', 22, 55, 5, 5, 5);

INSERT INTO `tbl_hist_ventas` (`Ventas_LibroId`, `Ventas_Vendido`) VALUES
(1, 10),
(2, 15),
(3, 8),
(4, 12),
(5, 25);

SELECT Libro.titulo, Autor.nombre, Autor.nacionalidad
FROM Libro
JOIN Autor ON Libro.id_autor = Autor.id_autor;


select 
	reg.Id,
    reg.Nombre,
    reg.A_Paterno,
    reg.A_Materno,tbl_ope_libro
    gru.Grupo,
    tur.Turno
 from registros reg
inner join tbl_grupo gru on reg.Grupo=gru.Id_Grupo
inner join tbl_turno tur on reg.Turno=tur.Id_Turno;


select 
	lib.LibroId,
    lib.Libro_Nombre,
    lib.Libro_Costo,
    lib.Libro_Cantidad,
    gen.Genero_Nombre,
    edi.Editorial_Nombre,
    idi.Idioma_Nombre
from tbl_ope_libro lib
inner join tbl_cat_editorial edi on lib.Libro_EditorialId=edi.EditorialId
inner join tbl_cat_genero gen on lib.Libro_GeneroId=gen.GeneroId
inner join tbl_cat_idioma idi on lib.Libro_IdiomaId=idi.IdiomaId;