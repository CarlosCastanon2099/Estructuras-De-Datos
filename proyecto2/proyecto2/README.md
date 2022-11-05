**Estructuras de Datos**
====================

# **Proyecto 2** 🔴〰️⚫

---------------------------------------------------


**Alumno**

319053315 - Castañón Maldonado Carlos Emilio


---

## **Uso**

- Compilar desde `proyecto2/`:

```
mvn compile
```

- Instalar desde `proyecto2/` para generar el archivo proyecto2.jar en el subdirectorio target:

```
mvn install
```


---------------------------------------------------
 Uso 
- El programa contempla para su uso lo pedido, sin embargo a modo de prueba he dejado por default un archivo, el cual es prueba.txt el cual contiene la informacion para construir un arbol rojinegro a modo de "test".
---
- Para correr el programa con el ejemplo mencionado
-  Ejecutamos desde proyecto2/ 
```
java -jar target/proyecto2.jar prueba.txt
```
- Lo cual nos generara lo siguiente 
```
El Codigo de tu SVG es: 

<?xml version='1.0' encoding='UTF-8' ?><svg width='980' height='490'><g><line x1='50' y1='310' x2='160' y2='180' stroke='black' stroke-width='3' /><line x1='160' y1='180' x2='270' y2='310' stroke='black' stroke-width='3' /><line x1='160' y1='180' x2='380' y2='50' stroke='black' stroke-width='3' /><line x1='490' y1='310' x2='600' y2='180' stroke='black' stroke-width='3' /><line x1='380' y1='50' x2='600' y2='180' stroke='black' stroke-width='3' /><line x1='710' y1='440' x2='820' y2='310' stroke='black' stroke-width='3' /><line x1='600' y1='180' x2='820' y2='310' stroke='black' stroke-width='3' /><line x1='820' y1='310' x2='930' y2='440' stroke='black' stroke-width='3' /><circle cx='50' cy='310' r='40' stroke='black' stroke-width='3' fill='black' /><text x='50' y='315' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>1</text><circle cx='160' cy='180' r='40' stroke='black' stroke-width='3' fill='red' /><text x='160' y='185' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>2</text><circle cx='270' cy='310' r='40' stroke='black' stroke-width='3' fill='black' /><text x='270' y='315' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>3</text><circle cx='380' cy='50' r='40' stroke='black' stroke-width='3' fill='black' /><text x='380' y='55' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>4</text><circle cx='490' cy='310' r='40' stroke='black' stroke-width='3' fill='black' /><text x='490' y='315' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>5</text><circle cx='600' cy='180' r='40' stroke='black' stroke-width='3' fill='red' /><text x='600' y='185' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>6</text><circle cx='710' cy='440' r='40' stroke='black' stroke-width='3' fill='red' /><text x='710' y='445' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>7</text><circle cx='820' cy='310' r='40' stroke='black' stroke-width='3' fill='black' /><text x='820' y='315' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>8</text><circle cx='930' cy='440' r='40' stroke='black' stroke-width='3' fill='red' /><text x='930' y='445' text-anchor='middle' font-family='Courier New' font-size='20' fill='white'>9</text></g></svg>

Tu SVG se genero y guardo exitosamente en la raiz de esta carpeta 

```

- Lo cual genera el codigo del .svg en bruto en la terminal y tambien genera un archivo .svg en la carpeta en la que se encuentra el pom.xml

- El programa cumple con lo pedido (excepto colas) por lo que dependiendo de la estructura de datos a graficar el archivo .svg sera diferente.
- Quedando asi que para ejecutar el programa necesitamos tener el archivo.txt con la estructura de datos a graficar y sus elementos en la carpeta donde se encuentra el archivo pom.xml
  
```
java -jar target/proyecto2.jar archivo.txt
```


----------------------------------------------------------------

