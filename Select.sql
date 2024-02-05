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