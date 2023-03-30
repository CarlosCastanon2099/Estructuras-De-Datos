**Estructuras de Datos**
====================

# **Proyecto 1** 🎮🥊

---------------------------------------------------


**Carlos C**


---

## **Uso**

- Compilar desde `proyecto1/`:

```
mvn compile
```

- Instalar desde `proyecto1/` para generar el archivo proyecto1.jar en el subdirectorio target:

```
mvn install
```


---------------------------------------------------
 Uso 
- El programa contempla para su uso lo pedido, sin embargo a modo de prueba he dejado por default tres archivos, el primero es hombres4.txt el cual contiene el poema de hombres necios, el segundo es mortal.txt el cual contiene el poema de con el dolor de la mortal herida y por ultimo prueba.txt el cual esta vacio y su funcion es la de probar la bandera de guardado.
---
- Para correr el programa con los ejemplos mencionados
-  Ejecutamos desde proyecto1/ 
-  Una de las siguientes dos opciones
```
java -jar target/proyecto1.jar hombres4.txt
```
```
java -jar target/proyecto1.jar mortal.txt
```
- Para correr el programa a través de la entrada estándar
- Ejecutamos desde proyecto1/ 
-  Una de las siguientes dos opciones
```
cat hombres4.txt | java -jar target/proyecto1.jar
```
```
cat mortal.txt | java -jar target/proyecto1.jar
```

- Para probar con la bandera de reversa
- Ejecutamos desde proyecto1/ 
-  Una de las siguientes dos opciones
```
java -jar target/proyecto1.jar hombres4.txt -r
```
```
cat mortal.txt | java -jar target/proyecto1.jar -r mortal.txt
```

- Para probar el programa con mas de un archivo y con la bandera reversa
- Ejecutamos desde proyecto1/ 

```
java -jar target/proyecto1.jar hombres4.txt mortal.txt -r
```

- En caso de que queramos lo anterior en un archivo de texto basta con añadir la bandera de guardado a lo anterior y indicar que lo queremos guardar en el archivo prueba.txt 
- Quedando asi 
  
```
java -jar target/proyecto1.jar hombres4.txt mortal.txt -r -o prueba.txt
```


----------------------------------------------------------------

## **- Nota**

<div align="justify">
Todo lo anterior fue solo un ejemplo de uso, el programa tambien puede ordenar los txt de otras locaciones y con infinidad de combinaciones que no puse.
  Como observacion yo contemplo que al descomprimir el tar.gz el orden de todo queda como proyecto1/src, tal que cuando pongo que se ejecute algo desde /proyecto1 me refiero a la carpeta en la que se encuentra src y los archivos txt de prueba.
</div>
