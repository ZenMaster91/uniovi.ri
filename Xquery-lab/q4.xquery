<q4>
{
for $l in doc("bib.xml") //libro
  where count($l/autor)>0
    return 
    <libro>
   {
     <titulo> { string($l/titulo) } </titulo>,
     <autor> { string($l/autor[1]/nombre) } </autor>
   } 
   {
     if(count($l/autor) > 1) then <hay-mas-autores> { count($l/autor) } </hay-mas-autores> else ()
   }
   </libro>
}
</q4>
