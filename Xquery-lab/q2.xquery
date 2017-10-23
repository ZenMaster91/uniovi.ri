<libros-y-años-entre-1991-y-1999>
{
for $l in doc("bib.xml") //libro
 let $p := $l/@año
 where $p < 1999 and $p >1991
   return
   <libro>
   {
     <titulo> { string($l/titulo) } </titulo>,
     <precio> { string($l/@año) } </precio>
   }
   </libro>
}
</libros-y-años-entre-1991-y-1999>
