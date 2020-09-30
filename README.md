# RookMotion_Prueba
PruebaRookMotion


Autenticación
Existen 2 activities principales:
LoginActivity y MainActivity (HOME). En el MainActivity Estan Implementados 4 Fragmentos. HOME, y las 3 funciones siguiente. Fragmento para Bluetooth, Buscar Canciones y compartirlas ,   y tomar foto y compartirla por facebook.

Si estas ya logueado permanece en MainActivity, de lo contrario se implementa un intento para llegar a la activity Login.

Para autenticarte con tu cuenta personal no podrás hacerlo porq la APP falta que la revise Facebook. Asi que solo usuarios de prueba pueden accesar. O bien me puedes dar solo el link de tu Facebook y te agrego al proyecto. Sino te paso un usuario de prueba que hice.

user:              ybfsatiksy_1601358451@tfbnw.net
passsword:    youyouyou

Sugiero que antes de loguearte cierres sesión en tu chrome o en tu app de Facebook.
Puedes ingresar al perfil que te mande desde tu app de facebook o navegador y  ver lo que has pubicado para corroborar que la app jala bien.

API REST.
Lyrics.OVH es un servicio simple de búsqueda de letras de canciones basados en una
consulta abierta por Artista o Título. El servicio de API se respalda en información de
Deezer, entre otras fuentes, para encontrar las coincidencias musicales relacionadas con
búsquedas.

El proyecto incluye la arquitectura Android y a su vez implementé los patrones singleton y MVVM. Utilicé navigation para la navegación entre framentos. Esta hecho en lenguaje JAVA.

La arquitectura Android se presenta a continuación:

Model
Repository
ViewModel
View

Además puedes buscar y compartir en Facebook la canción.

Bluetooth
Al ingresar en esta parte se activará el bluetooth sino lo tienes activado y hará un busqueda en la zona para encontrar dispositivos visibles. Te mostrará una lista con si dirección MAC nombre y si esta vinculado

Cámara
Toma un foto y compartela en Facebook falto esa parte del video ahora lo envio.
