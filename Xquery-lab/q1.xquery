<libros-y-precio-entre-50-y-250>
{
for $l in doc("bib.xml") //libro
 let $p := $l/precio
 where $p < 250 and $p >50
   return
   <libro>
   {
     <titulo> { string($l/titulo) } </titulo>,
     <precio> { string($l/precio) } </precio>
   }
   </libro>
}
</libros-y-precio-entre-50-y-250>
