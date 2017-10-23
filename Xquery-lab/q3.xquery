<libros-publicados-antes-del-2010-con-1-autor>
{
for $l in doc("bib.xml") //libro[count(autor)=1]
  where $l/@año < 2010
   return
   <libro>
   {
     <titulo> { string($l/titulo) } </titulo>,
     <precio> { string($l/@año) } </precio>,
     <autores> { count($l/autor) } </autores>
   }
   </libro>
}
</libros-publicados-antes-del-2010-con-1-autor>
