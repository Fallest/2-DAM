using System;
using System.IO;
using System.Linq;

/**
 * Programa D:
 * Un programa que muestre el nombre del autor de un fichero de música en formato MP3
 * (tendrás que localizar en Internet la información sobre dicho formato y sobre la cabecera ID3 V1,
 * que se encuentra -si está presente- en los últimos 128 bytes del fichero) Guarda el Título y el
 * autor en un fichero con el mismo nombre y extensión .txt.
 */

namespace Actividad4 {
    class Program {
        static void Main(string[] args) {
            // Pedimos el nombre del fichero
            string archivo = PedirNombre();
            // Creamos el archivo .txt con la información de un archivo .mp3, si existe.
            // ExtraerAutorID3V1(archivo);
            ExtraerAutorID3V2_3(archivo);
        }

        private static void ExtraerAutorID3V1(string archivo) {
            /**
             * 1º: Comprobamos que existe la información que buscamos en el archivo
             * 2º: Si existe, la extraemos en una cadena
             * 3º: Si se cumple el 2º, creamos un archivo en la misma localización que el .mp3,
             * con el mismo nombre y con extensión .txt.
             * 4º: Escribimos en dicho archivo la cadena con la información extraída.
             *
             * Para comprobar si existe información, tenemos que ir al final del archivo (seek the end).
             * Retroceder 128 bytes (-128).
             * Comprobar si los tres primeros btyes de esos 128 deletrean TAG.
             * Si los 3 primeros btyes son TAG, la información está descrita tal que así:
             * Bytes 0..2: 'TAG'
             * Bytes 3..32: Título (30 bytes)
             * Bytes 33..62: Artista (30 bytes)
             * Bytes 63..92: Álbum (30 btyes)
             * Bytes 93..96: Año de lanzamiento (4 bytes)
             * Bytes 97..126: Comentario/Descripción (30 bytes)
             * Byte 127: Identificador de género (existe una lista para saber qué número es cuál género)
             *** La lista de géneros se encuentra en el final de este archivo.
             */
            try {
                FileStream lector = new FileStream(archivo, FileMode.Open);
                // Retrocede 128 posicioes desde el final
                lector.Seek(-128, SeekOrigin.End);
                
                /**
                 * Leemos los 3 primeros bytes, creando un array de tipo byte[] en el proceso.
                 * Usando System.Text.Encoding.UTF8, podemos traducir un array de bytes a una cadena de caracteres UTF-8.
                 */
                string contenido = System.Text.Encoding.UTF8.GetString(new byte[] {(byte) lector.ReadByte(), 
                    (byte) lector.ReadByte(), (byte) lector.ReadByte()});
                
                if (contenido.Equals("TAG")) {
                    /** Si entramos en este if, hay información almacenada.
                     * Vamos a leer el título y el artista.
                     * Para ello tenemos que crear un array de bytes, el cual vamos a llenar con los bytes correspondientes
                     * a cada dato.
                     */
                    byte[] tituloB = new byte[30];
                    byte[] artistaB = new byte[30];
                    /**
                     * Con Read le indicamos dónde queremos guardar los bytes leídos, dónde empezar y cuántos bytes leer.
                     * Como ya hemos avanzado 3 bytes (estamos en la posición 125 empezando desde el final), ya estamos
                     * posicionados para leer el título y después el artista
                     */
                    lector.Read(tituloB, (int) SeekOrigin.Current, 30);
                    lector.Read(artistaB, (int) SeekOrigin.Current, 30);
                    
                    /**
                     * Ahora vamos a escribir estos dos datos en un archivo.txt.
                     * Primero creamos el archivo.
                     * Luego, con un flujo, escribimos los datos en él.
                     *
                     * Para obtener el nombre para nuestro .txt, vamos a usar la función Split(delimitante) de string.
                     * Esta función va a separar la cadena desde la que se le llama por el carácter delimitante, devolviendo
                     * un array de cadenas.
                     * En este caso, buscamos separar por ".", porque a la ruta que nos han dado queremos quitarle solo
                     * el ".mp3" del final, y cambiarlo por un ".txt".
                     * Al separar la ruta por ".", tenemos dos partes: "C:\...\archivo" y "mp3".
                     * Nos quedamos con el primer elemento (el 0) y le añadimos ".txt" al final.
                     */
                    string archivoTxt = archivo.Split(".")[0] + ".txt";
                    using FileStream escritor = new FileStream(archivoTxt, FileMode.Create);
                    
                    escritor.Write(tituloB, (int) SeekOrigin.Current, 30);
                    // Para escribir un salto de línea tenemos que convertir el carácter \n en un Byte.
                    escritor.WriteByte(Convert.ToByte("\n"));
                    escritor.Write(artistaB, (int) SeekOrigin.Current, 30);
                }
                else {
                    Console.WriteLine("Error: El archivo .mp3 especificado no contiene una cabecera ID3.");
                    Environment.Exit(-1);
                }
            }
            catch (FileNotFoundException ex) {
                Console.WriteLine("Error: Archivo no encontrado.");
                Environment.Exit(-1);
            }
        }

        private static void ExtraerAutorID3V2_3(string archivo) {
            /**
             * Por desgracia, hoy en día la mayoría de archivos .mp3 usan una versión más novedosa de ID3: la versión
             * 2. En concreto la versión 2.3.0.
             * Esto significa que probablemente, no vamos a poder comprobar en casa que nuestro método para extraer
             * la información de un .mp3 con ID3v1 funciona.
             * Por tanto voy a hacer uno para ID3v2.3.0 :D .                                                            (ayuda D:)
             * 
             * La forma en la que funciona ID3v2.3.0 es la siguiente:
             * Bytes 0..2: 'ID3'
             * Bytes 3..4: La versión de ID3.
             * Byte 5: Flags: 'abc00000' (representación en bits del byte) a: Unsynchronisation, b: Exteded header,
             *      c: Experimetal indicator.
             * Bytes 6..9: Tamaño. Todos los bits más significativos de todos los bytes están a 0. Es decir, los 4 bytes
             *      pasados a bits se verían tal que '0xxxxxxx', donde x es 0 ó 1. Hay un total de 28 bits para
             *      designar el tamaño de la tag completa después de la de desinconización, incluyendo el padding,
             *      excluyendo el header (estos primeros 10 bytes) pero sin excluir el extended header.
             *      Es decir, el tamaño será hasta 256 MB (28 bits), y estos 4 bytes representan el
             *      tamaño del tag - 10 (en bytes).
             * 
             * Bytes 10..hasta los bytes indicados por el tamaño + 10 (porque están desplazados 10 posiciones, por el header):
             *      Esto es el Extended header (solo está presente si el bit b del byte 5 está 'set', es decir, es 1).
             *      El Extended header tiene su propio apartado de 10 btyes:
             *      Bytes 0..3: Tamaño del extended header.
             *      Bytes 4..5: Extended flags: son 16 bits (2 bytes), de los cuales el más significativo (la x en
             *      %x0000000 00000000) indica si hay 4 bytes para datos de CRC-32 añadidos al extended header.
             *      Bytes 6..9: Tamaño del padding: el tamaño del padding es simplemente el tamaño de la tag, restando
             *              el tamaño de los frames y  de los headers.
             *
             * Ahora vamos a ver una descripción de los frames, que también tienen su desglose en bytes:
             * Bytes 0..3: ID del Frame. Existe una lista para indicar qué tipo de dato es el que se va a estar almacenando
             *      en este frame: los que nos importan son TIT2 (el título) y TPE1 (el artista).
             ***    Al final de este archivo se encuentra una lista con los Frame IDs de cada elemento.
             * Bytes 4..7: Tamaño del contenido del frame, que es el tamaño del frame excluyendo el header del frame (10 btyes).
             * Bytes 8..9: Flags. No entraremos en detalle puesto que ahora mismo no nos es útil.
             *
             * Entonces tenemos que tener las siguientes cosas en cuenta para encontrar los dos campos que buscamos:
             * -Los primeros tres bytes tienen que representar 'ID3'.
             * -En el byte 5, el séxtimo bit más significativo debe estar 'set'.
             * -Debemos buscar, entre los bytes 9 y hasta los bytes representados por los bytes 6..9, frames que contengan
             * en sus 4 primeros bytes las cadenas 'TIT2' y 'TPE1'.
             * -Una vez encontrados los frames cuyo Frame ID coincida con alguno de los anteriormente mencionados,
             * tenemos que leer el tamaño del contenido del tag (almacenado en los bytes 4..7 del header del frame) para
             * leer todos los bytes desde la posición (10 + tamañoExtendedHeader + posición en la que encontramos 'TIT2' o
             * 'TPE1' + 6) hasta (10 + tamañoExtendedHeader + posición en la que encontramos 'TIT2' o 'TPE1' + 6 +
             * tamaño del contenido del frame). Esto básicamente se traduce en este código:
             * lector.Read(byte[tamContenidoFrame], posiciónFrameID + 6, tamContenidoFrame);
             * -Una vez almacenados los dos datos que buscamos, los escribiremos en un archivo, con el mismo nombre
             * que el .mp3, pero con extensión .txt.
             */
            try {
                // Creamos un flujo para leer del archivo.
                using FileStream lector = new FileStream(archivo, FileMode.Open);
                // Creamos los arrays de bytes para los datos, pero no les asignamos un tamaño aún.
                byte[] artistaB;
                byte[] tituloB;
                
                // Comprobamos que los tres primeros bytes son ID3
                if (System.Text.Encoding.UTF8.GetString(new byte[] {
                    (byte) lector.ReadByte(), (byte) lector.ReadByte(), (byte) lector.ReadByte()
                }).Equals("ID3")) {
                    /**
                     * Hemos leído los tres primeros bytes con ayuda de un casting a byte ->
                     * los hemos añadido a un byte array sin nombre ->
                     * los hemos convertido a string usando System.Text.Encodig.UTF8 ->
                     * hemos comparado con Equals que equivale a "ID3".
                     */
                    
                    // Ahora debemos ir al 5º byte (tenemos que saltar 5 posiciones desde el inicio) y comprobar
                    // que es igual o superior a 64 (%a1c00000 pasado de base 2 a base 10).
                    lector.Seek(4, SeekOrigin.Begin);
                    if (Convert.ToInt32(lector.ReadByte()) >= 64) throw new Exception();
                    
                    // Extraemos el tamaño del Extended Header: bytes 6º al 9º.
                    byte[] tamExtHeaderB = new byte[4];
                    lector.Read(tamExtHeaderB);
                    Reverse(tamExtHeaderB, out tamExtHeaderB);
                    // BitConverter ayuda a convertir un array de bytes a un entero.
                    // Ahora tenemos el límite hasta el que debemos intentar encontrar los Frame IDs.
                    int tamExtHeader = BitConverter.ToInt32(tamExtHeaderB, 0);
                    
                    // Vamos a extraer el conteido de la tag entera (desde la posición 10 hasta tamExtHeader):
                    byte[] contenidoTag = new byte[tamExtHeader];
                    lector.Read(contenidoTag);
                    
                    // Vamos a convertir el contenido en una cadena y vamos a buscar en ella el índice de TIT2 y TPE1.
                    string titulo = System.Text.Encoding.UTF8.GetString(contenidoTag);
                    
                    int indTitulo = titulo.IndexOf("TIT2", StringComparison.Ordinal) + 4;
                    int indArtista = titulo.IndexOf("TPE1", StringComparison.Ordinal) + 4;
                    
                    /**
                     * Ahora, sabiendo que tras los 4 bytes del Frame ID, hay otros 4 bytes que indican el tamaño del
                     * contenido del frame y 2 más para tags, podemos extraer el contenido de cada frame: es decir,
                     * el título y el álbum.
                     */
                    byte[] tamContTituloB;
                    tamContTituloB = new[] {
                        contenidoTag[indTitulo],
                        contenidoTag[indTitulo + 1],
                        contenidoTag[indTitulo + 2],
                        contenidoTag[indTitulo + 3]
                    };
                    Reverse(tamContTituloB, out tamContTituloB);
                    int tamContTitulo = BitConverter.ToInt32(tamContTituloB);
                    Extract(contenidoTag, indTitulo + 7, indTitulo + 6 + tamContTitulo, out tituloB);
                    
                    byte[] tamContArtistaB;
                    tamContArtistaB = new[] {
                        contenidoTag[indArtista],
                        contenidoTag[indArtista + 1],
                        contenidoTag[indArtista + 2],
                        contenidoTag[indArtista + 3]
                    };
                    Reverse(tamContArtistaB, out tamContArtistaB);
                    int tamContArtista = BitConverter.ToInt32(tamContArtistaB);
                    Extract(contenidoTag, indArtista + 7, indArtista + 6 + tamContArtista, out artistaB);
                    
                    // Ahora que tenemos los datos en los arrays de bytes, los vamos a escribir en un archivo.
                    string archivoTxt = archivo.Split(".")[0] + ".txt";
                    using FileStream escritor = new FileStream(archivoTxt, FileMode.Create);
                    
                    escritor.Write(tituloB);
                    // Para escribir un salto de línea tenemos que convertir el carácter \n en un Byte.
                    escritor.WriteByte(Convert.ToByte('\n'));
                    escritor.Write(artistaB);
                }
            }
            catch (FileNotFoundException ex) {
                Console.WriteLine("Error: No se ha encontrado el archivo.");
            }
        }

        private static void Reverse(byte[] tamExtHeaderB, out byte[] res) {
            /**
             * Método para invertir un array de bytes.
             */
            res = new byte[tamExtHeaderB.Length];
            int j = 0;
            for (int i = tamExtHeaderB.Length - 1; i >= 0; i--) {
                res[j] = tamExtHeaderB[i];
                j++;
            }
        }

        private static string PedirNombre() {
            string archivo = "";

            try {
                Console.Write("Introduzca la ruta al archivo: ");
                archivo = Console.ReadLine();

                if (archivo != null && archivo.Split(".")[1] != "mp3")
                    throw new Exception();
            }
            catch (IOException ex) {
                Console.WriteLine("Se han introducido caracteres no válidos. \nSaliendo del programa...");
                System.Environment.Exit(-1);
            }
            catch (Exception ex) {
                Console.WriteLine("Error. No es un archivo .mp3");
            }

            return archivo;
        }
        
        private static void Extract<T>(T[] array, int beginIndex, int endIndex, out T[] res) {
            /**
             * Esta función va a extraer el contenido que hay en un array entre los elementos beginIndex y endIndex.
             * Crea un array auxiliar, usando la función Take (extrae los elementos HASTA el índice indicado),
             * y luego, devuelve un array, en el que tras haber extraído la primera parte que incluye los índices
             * que queremos extraer, devuelve lo que hay DESDE el indice indicado con Skip.
             * Por ejemplo, si tenemos una cadena tal que así: "abcde"
             * Y queremos extraer "bc", esta función realiza lo siguiente:
             * aux = "abc" (Tomamos "abc" de "abcde")
             * res = "bc" (Saltamos la "a" y nos quedamos con "bc")
             *
             * NOTA: beginIndex es EXCLUYENTE y endIndex es INCLUYENTE.
             */
            T[] aux = array.Take(endIndex).ToArray();
            res = aux.Skip(beginIndex).ToArray();
        }
    }
}



/*
 * Lista de Géneros:
 * 0 - Blues
 * 1 - Classic Rock
 * 2 - Country
 * 3 - Dance
 * 4 - Disco
 * 5 - Funk
 * 6 - Grunge
 * 7 - Hip-Hop
 * 8 - Jazz
 * 9 - Metal
 * 10 - New Age
 * 11 - Oldies
 * 12 - Other
 * 13 - Pop
 * 14 - R&B
 * 15 - Rap
 * 16 - Reggae
 * 17 - Rock
 * 18 - Techno
 * 19 - Industrial
 * 20 - Alternative
 * 21 - Ska
 * 22 - Death Metal
 * 23 - Pranks
 * 24 - Soundtrack
 * 25 - Euro-Techno
 * 26 - Ambient
 * 27 - Trip-Hop
 * 28 - Vocal
 * 29 - Jazz+Funk
 * 30 - Fusion
 * 31 - Trance
 * 32 - Classical
 * 33 - Instrumental
 * 34 - Acid
 * 35 - House
 * 36 - Game
 * 37 - Sound Clip
 * 38 - Gospel
 * 39 - Noise
 * 40 - Alternative Rock
 * 41 - Bass
 * 42 - Soul
 * 43 - Punk
 * 44 - Space
 * 45 - Meditative
 * 46 - Instrumental Pop
 * 47 - Instrumental Rock
 * 48 - Ethnic
 * 49 - Gothic
 * 50 - Darkwave
 * 51 - Techno-Industrial
 * 52 - Electronic
 * 53 - Pop-Folk
 * 54 - Eurodance
 * 55 - Dream
 * 56 - Southern Rock
 * 57 - Comedy
 * 58 - Cult
 * 59 - Gangsta
 * 60 - Top 40
 * 61 - Christian Rap
 * 62 - Pop/Funk
 * 63 - Jungle
 * 64 - Native US
 * 65 - Cabaret
 * 66 - New Wave
 * 67 - Psychadelic
 * 68 - Rave
 * 69 - Showtunes
 * 70 - Trailer
 * 71 - Lo-Fi
 * 72 - Tribal
 * 73 - Acid Punk
 * 74 - Acid Jazz
 * 75 - Polka
 * 76 - Retro
 * 77 - Musical
 * 78 - Rock & Roll
 * 79 - Hard Rock
 * 80 - Folk
 * 81 - Folk-Rock
 * 82 - National Folk
 * 83 - Swing
 * 84 - Fast Fusion
 * 85 - Bebob
 * 86 - Latin
 * 87 - Revival
 * 88 - Celtic
 * 89 - Bluegrass
 * 90 - Avantgarde
 * 91 - Gothic Rock
 * 92 - Progressive Rock
 * 93 - Psychedelic Rock
 * 94 - Symphonic Rock
 * 95 - Slow Rock
 * 96 - Big Band
 * 97 - Chorus
 * 98 - Easy Listening
 * 99 - Acoustic
 * 100 - Humour
 * 101 - Speech
 * 102 - Chanson
 * 103 - Opera
 * 104 - Chamber Music
 * 105 - Sonata
 * 106 - Symphony
 * 107 - Booty Bass
 * 108 - Primus
 * 109 - Porn Groove
 * 110 - Satire
 * 111 - Slow Jam
 * 112 - Club
 * 113 - Tango
 * 114 - Samba
 * 115 - Folklore
 * 116 - Ballad
 * 117 - Power Ballad
 * 118 - Rhythmic Soul
 * 119 - Freestyle
 * 120 - Duet
 * 121 - Punk Rock
 * 122 - Drum Solo
 * 123 - Acapella
 * 124 - Euro-House
 * 125 - Dance Hall
 * 126 - Goa
 * 127 - Drum & Bass
 * 128 - Club - House
 * 129 - Hardcore
 * 130 - Terror
 * 131 - Indie
 * 132 - BritPop
 * 133 - Negerpunk
 * 134 - Polsk Punk
 * 135 - Beat
 * 136 - Christian Gangsta Rap
 * 137 - Heavy Metal
 * 138 - Black Metal
 * 139 - Crossover
 * 140 - Contemporary Christian
 * 141 - Christian Rock
 * 142 - Merengue
 * 143 - Salsa
 * 144 - Thrash Metal
 * 145 - Anime
 * 146 - JPop
 * 147 - Synthpop
 * 148 - Unknown
 *
 * Lista de FrameIDs:
 * 4.20    AENC    [[#sec4.20|Audio encryption]]
 * 4.15    APIC    [#sec4.15 Attached picture]
 * 4.11    COMM    [#sec4.11 Comments]
 * 4.25    COMR    [#sec4.25 Commercial frame]
 * 4.26    ENCR    [#sec4.26 Encryption method registration]
 * 4.13    EQUA    [#sec4.13 Equalization]
 * 4.6     ETCO    [#sec4.6 Event timing codes]
 * 4.16    GEOB    [#sec4.16 General encapsulated object]
 * 4.27    GRID    [#sec4.27 Group identification registration]
 * 4.4     IPLS    [#sec4.4 Involved people list]
 * 4.21    LINK    [#sec4.21 Linked information]
 * 4.5     MCDI    [#sec4.5 Music CD identifier]
 * 4.7     MLLT    [#sec4.7 MPEG location lookup table]
 * 4.24    OWNE    [#sec4.24 Ownership frame]
 * 4.28    PRIV    [#sec4.28 Private frame]
 * 4.17    PCNT    [#sec4.17 Play counter]
 * 4.18    POPM    [#sec4.18 Popularimeter]
 * 4.22    POSS    [#sec4.22 Position synchronisation frame]
 * 4.19    RBUF    [#sec4.19 Recommended buffer size]
 * 4.12    RVAD    [#sec4.12 Relative volume adjustment]
 * 4.14    RVRB    [#sec4.14 Reverb]
 * 4.10    SYLT    [#sec4.10 Synchronized lyric/text]
 * 4.8     SYTC    [#sec4.8 Synchronized tempo codes]
 * 4.2.1   TALB    [#TALB Album/Movie/Show title]
 * 4.2.1   TBPM    [#TBPM BPM (beats per minute)]
 * 4.2.1   TCOM    [#TCOM Composer]
 * 4.2.1   TCON    [#TCON Content type]
 * 4.2.1   TCOP    [#TCOP Copyright message]
 * 4.2.1   TDAT    [#TDAT Date]
 * 4.2.1   TDLY    [#TDLY Playlist delay]
 * 4.2.1   TENC    [#TENC Encoded by]
 * 4.2.1   TEXT    [#TEXT Lyricist/Text writer]
 * 4.2.1   TFLT    [#TFLT File type]
 * 4.2.1   TIME    [#TIME Time]
 * 4.2.1   TIT1    [#TIT1 Content group description]
 * 4.2.1   TIT2    [#TIT2 Title/songname/content description]
 * 4.2.1   TIT3    [#TIT3 Subtitle/Description refinement]
 * 4.2.1   TKEY    [#TKEY Initial key]
 * 4.2.1   TLAN    [#TLAN Language(s)]
 * 4.2.1   TLEN    [#TLEN Length]
 * 4.2.1   TMED    [#TMED Media type]
 * 4.2.1   TOAL    [#TOAL Original album/movie/show title]
 * 4.2.1   TOFN    [#TOFN Original filename]
 * 4.2.1   TOLY    [#TOLY Original lyricist(s)/text writer(s)]
 * 4.2.1   TOPE    [#TOPE Original artist(s)/performer(s)]
 * 4.2.1   TORY    [#TORY Original release year]
 * 4.2.1   TOWN    [#TOWN File owner/licensee]
 * 4.2.1   TPE1    [#TPE1 Lead performer(s)/Soloist(s)]
 * 4.2.1   TPE2    [#TPE2 Band/orchestra/accompaniment]
 * 4.2.1   TPE3    [#TPE3 Conductor/performer refinement]
 * 4.2.1   TPE4    [#TPE4 Interpreted, remixed, or otherwise modified by]
 * 4.2.1   TPOS    [#TPOS Part of a set]
 * 4.2.1   TPUB    [#TPUB Publisher]
 * 4.2.1   TRCK    [#TRCK Track number/Position in set]
 * 4.2.1   TRDA    [#TRDA Recording dates]
 * 4.2.1   TRSN    [#TRSN Internet radio station name]
 * 4.2.1   TRSO    [#TRSO Internet radio station owner]
 * 4.2.1   TSIZ    [#TSIZ Size]
 * 4.2.1   TSRC    [#TSRC ISRC (international standard recording code)]
 * 4.2.1   TSSE    [#TSEE Software/Hardware and settings used for encoding]
 * 4.2.1   TYER    [#TYER Year]
 * 4.2.2   TXXX    [#TXXX User defined text information frame]
 * 4.1     UFID    [#sec4.1 Unique file identifier]
 * 4.23    USER    [#sec4.23 Terms of use]
 * 4.9     USLT    [#sec4.9 Unsychronized lyric/text transcription]
 * 4.3.1   WCOM    [#WCOM Commercial information]
 * 4.3.1   WCOP    [#WCOP Copyright/Legal information]
 * 4.3.1   WOAF    [#WOAF Official audio file webpage]
 * 4.3.1   WOAR    [#WOAR Official artist/performer webpage]
 * 4.3.1   WOAS    [#WOAS Official audio source webpage]
 * 4.3.1   WORS    [#WORS Official internet radio station homepage]
 * 4.3.1   WPAY    [#WPAY Payment]
 * 4.3.1   WPUB    [#WPUB Publishers official webpage]
 * 4.3.2   WXXX    [#WXXX User defined URL link frame]
 */