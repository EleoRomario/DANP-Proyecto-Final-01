package com.idnp.danp_proyecto_final.data

import com.idnp.danp_proyecto_final.R

data class DestinosData (
    val codeDep:String,
    val code:String,
    val title:String,
    val category:String,
    val img: Int,
    val description: String,
    val latitud: Double,
    val longitud: Double,
)
val destinosList: List<DestinosData> = listOf(

    DestinosData(
        "amazonas",
        "ama_01",
        "Las cataratas de Gocta",
        "aventura",
        R.drawable.cataratasdegocta,
        "Es una de las más altas del planeta, con saltos de agua de más de 700 metros. Se ubica en la provincia de Bongará. Está compuesta por dos caídas de agua, rodeadas de abundante vegetación y muchas especies de animales, algunas en peligro de extinción.",
        -6.0230555555556,
        -77.887222222222
    ),
    DestinosData(
        "amazonas",
        "ama_02",
        "Catarata de Yumbilla",
        "aventura",
        R.drawable.cataratadeyumbilla,
        "Reconocida como una de las cataratas más grandes del mundo, con 895 metros de altura. Está situada en el distrito de Cuipes, provincia de Bongará. Para llegar, se tiene que realizar una pequeña ruta de trekking que vale la pena completar para observar el paisaje.",
        -5.9199,
        -77.9012,
    ),
    DestinosData(
        "amazonas",
        "ama_03",
        "Laguna de Burlan",
        "aventura",
        R.drawable.lagunaburlan,
        "Este recurso turístico es conocido también como Laguna de Los Ángeles, tiene una extensión de 0.5 kilómetros cuadrados, su origen se debe a la constante acumulación de aguas de las tierras agrícolas que la rodean; Por el lado derecho y la zona sur de la laguna se puede apreciar cantidad de andenes arroceros, sus aguas son de temperatura cálida lo que le ha dado un ecosistema único y muy especial.",
        -5.7873,
        -78.3827,
    ),
    DestinosData(
        "amazonas",
        "ama_04",
        "Mausoleos de Revash",
        "cultural",
        R.drawable.arequipa,
        "Centro funerario construido por los Chachapoyas en la pared rocosa de un pronunciado barranco. Solo se aprecian sus fachadas, con nichos cuadrangulares, en forma de cruz y de “T”. Algunos de sus sectores muestran pinturas rupestres de color ocre que representan animales y personas.",
        -6.5407,
        -77.8565,
    ),
    DestinosData(
        "amazonas",
        "ama_05",
        "Los Sarcófagos de Karajía",
        "cultural",
        R.drawable.sarcofagokarajia,
        "Se trata de un conjunto de sarcófagos o tumbas de la cultura Chachapoyas, ubicados en el distrito de Luya del Departamento de Amazonas. Estos cinco colosos de figura humana de dos metros y medio de altura fueron elaborados con arcilla, paja seca, cañas, palos, cuerdas vegetales y piedras. También conocidas como Purunmachus, palabra quechua que significa “hombre antiguo”, y a lo largo del acantilado hay un promedio de catorce más.",
        -6.16194444,
        -78.02136111,
    ),
    DestinosData(
        "amazonas",
        "ama_06",
        "Fortaleza de Kuélap",
        "cultural",
        R.drawable.kuelap,
        "Una joya arquitectónica construida por los habitantes del antiguo Perú y que resalta por la perfección de sus acabados, el monumental tamaño de sus edificaciones y lo complejo que era (sí, tiempo pasado) acceder a ella.",
        -6.41791667,
        -77.92333333,
    ),
    DestinosData(
        "ancash",
        "an_01",
        "Laguna 69",
        "aventura",
        R.drawable.laguna69,
        "Entre los lugares de interés en Ancash para los amantes del trekking está la Laguna 69. A dos horas y media en autobús desde Huaraz u hora y 20 minutos en taxi de Yungay, comprende un sendero de 14 kilómetros de distancia con alturas variables entre los 3868 y los 4600 metros sobre el nivel del mar.",
        -8.9927125,
        -77.6869576,
    ),
    DestinosData(
        "ancash",
        "an_02",
        "Chavín de Huantar",
        "cultural",
        R.drawable.chavindehuantar,
        "Chavín de Huántar está entre los más completos sitios arqueológicos en la región de Ancash. Debe su nombre a los antiguos Chavín que en el pasado poblaran la región erigiéndose como una de las más destacadas culturas antes de los incas. El pueblo se encuentra a menos de tres horas en taxi de la ciudad de Huaraz, y aunque las vías no están en las mejores condiciones, el panorama te cautivará.",
        -9.5936721,
        -77.1773384,
    ),
    DestinosData(
        "ancash",
        "an_03",
        "Parque Nacional Huascarán",
        "aventura",
        R.drawable.parquenacionalhuascaran,
        "El Parque Nacional Huascarán con sus 340000 hectáreas tiene jurisdicción en 10 provincias del departamento de Ancash desde Huaylas hasta Pomabamba. Por márgenes tiene el Callejón de Huaylas al oeste y el de Conchucos por el este. El más alto de sus montes níveos supera los 6700 metros sobre el nivel del mar, y su nieve alimenta las diversas lagunas que llaman a la aventura a turistas y curiosos del mundo entero.",
        -9.2266855,
        -77.447012,
    ),
    DestinosData(
        "ancash",
        "an_04",
        "Cordillera Huayhuash",
        "extremo",
        R.drawable.huayhuash,
        "La Cordillera Huayhuash es uno de los atractivos naturales de Ancash para los amantes del montañismo y el trekking. Esta cadena montañosa de casi 30 km² de extensión resulta un desafío para los turistas: si quieres maravillarte con sus paisajes, tienes que estar a la altura (literalmente) de las circunstancias. Porque para aprovechar al máximo los diversos recorridos a pie que se realizan en esta zona, tu cuerpo y físico tienen que estar acostumbrados a la poderosa altitud (de promedio, las elevaciones son de 4150 m s. n. m.) y climas extremos de esta blanca cordillera.",
        -10.3,
        -76.9,
    ),
    DestinosData(
        "ancash",
        "an_05",
        "Callejón de Huaylas",
        "aventura",
        R.drawable.callejondehuaylas,
        "Con una extensión de 40,627 km, el Callejón de Huaylas es un amplio valle andino abierto entre dos importantes cadenas de montañas: la Cordillera Blanca y la Cordillera Negra. Inicia en la Laguna Conococha a 4,100 metros sobre el nivel del mar y termina en el Cañon del Pato. Además, presenta una diversidad de microclimas debido a la altitud en que se encuentra.",
        -9.2666666666667,
        -77.116666666667,
    ),
    DestinosData(
        "ancash",
        "an_06",
        "Nevado Pastoruri",
        "extremo",
        R.drawable.pastoruri,
        "El Nevado Pastoruri es una montaña que pertenece a la Cordillera Blanca, tiene una altura de 5240m y se encuentra ubicada en la comunidad campesina de Cátac, a 365 Kms al norte de Lima, y a 35 Kms al sur de Huaraz, en la provincia de Recuay Ancash. El glaciar Pastoruri Huaraz es visitado por muchos turistas locales, nacionales e internacionales para disfrutar de un día rodeado de paisajes, naturaleza y nieve. Camino hacia el nevado, se puede observar la laguna de Patococha, para luego continuar con una visita al ojo de agua de Pumapampa, también conocido como la «fuente de la juventud» por el agua gasificada que emite del subsuelo, denominada también laguna de 7 colores. Además se puede observar las Puyas Raimondi, pinturas rupestres y el atractivo más importante del tour: la base del glaciar del nevado Pastoruri.",
        -9.92917,
        -77.1908,
    ),
    DestinosData(
        "ancash",
        "an_07",
        "Caraz",
        "aventura",
        R.drawable.caraz,
        "Caraz, capital de la provincia de Huaylas, es una espléndida y conocida ciudad en el Callejón de Huaylas. Es de los típicos destinos turísticos de Ancash de oferta versátil con sus caras arqueológica, aventurera y deportista.",
        -9.0483, -77.81083
    ),
    DestinosData(
        "apurimac",
        "apu_01",
        "Baños Termales de Cconoc",
        "aventura",
        R.drawable.cconoc,
        "Un lugar ideal para el relajamiento y donde los poderes medicinales se hacen realidad gracias a sus cálidas aguas, las cuales provienen del río Apurímac. En sus alrededores hay algarrobos, carrizales y cactus.",
        -13.5431537,-72.6409818
    ),
    DestinosData(
        "apurimac",
        "apu_02",
        "El Bosque de Piedras de Pampachiri",
        "cultural",
        R.drawable.bosquedepiedraspampachiri,
        "El bosque de piedras de Pampachiri se encuentra en el distrito de Pampachiri, enmarcado en el departamento de Apurímac. Este majestoso lugar tiene un área de 60 hectáreas. Asimismo, las formaciones rocosas que se encuentran allí, son producto de la erosión de dos volcanes el Qawarasu y el Sotaya, esto ocurrió hace mas de cuatro mil años.",
        -14.2799267,-73.4555052
    ),
    DestinosData(
        "apurimac",
        "apu_03",
        "Cañón del Río Apurímac",
        "aventura",
        R.drawable.caniondeapurimac,
        "El cañón del río Apurimac está considerado como uno de los más profundos del mundo con 4 691 metros de profundidad, con una cuenca de 350 kilómetros de largo, donde el agua ha labrado pacientemente el valle por millones de años. Para muchos es uno de los destinos más increíbles para el trekking, el canotaje que cubre casi todos los niveles de dificultad y porque las vistas son simplemente alucinantes.",
        -13.5481028,-72.7364711
    ),
    DestinosData(
        "apurimac",
        "apu_04",
        "Conjunto Arqueológico de Saywite",
        "cultural",
        R.drawable.saywite,
        "El conjunto arqueológico de Saywite está enmarcado dentro de una depresión topográfica muy significativa, siendo delimitado por dos pequeños riachuelos que convergen y cierran el pequeño valle interandino.",
        -13.5471994,-72.8052168
    ),
    DestinosData(
        "apurimac",
        "apu_05",
        "Laguna de Pacucha",
        "aventura",
        R.drawable.lagunapacucha,
        "La Laguna de Pacucha es considerada como una de las más grandes y bellas lagunas del Perú. Se ubica a 17 kilómetros de Andahuaylas. Su importancia radica en virtud a que su volumen y componentes nutritivos de sus aguas son productivos, además de ser tibias y casi dulces.",
        -13.6113885,-73.3276437
    ),
    DestinosData(
        "apurimac",
        "apu_06",
        "Santuario de Cocharcas",
        "cultural",
        R.drawable.cocharcas,
        "El Santuario de Cocharcas es un recinto histórico de Apurímac de origen colonial que se construyó en el siglo XVI y fue declarado monumento artístico en 1941. El templo alberga a la milagrosa imagen de Virgen de Cocharcas, símbolo de la fe y motivo de peregrinación popular en Apurímac desde hace muchos años. El templo es de estilo barroco construido con piedra sillar labrada.",
        -13.6100237,-73.7422863
    ),
    DestinosData(
        "apurimac",
        "apu_07",
        "Santuario Nacional de Ampay",
        "aventura",
        R.drawable.ampay,
        "El Santuario Nacional de Ampay está enclavado en medio de los Andes conformando una especie de “isla biológica”. Además, forma parte del concatenamiento de los picos nevados de la Cordillera de Vilcabamba y de los Andes del sur, con un rango altitudinal que va desde los 2,900 a los 5,235 m.s.n.m. En su interior se contempla una enorme diversidad de riachuelos y manantiales que le confieren una belleza e importancia única al lugar.",
        -13.5773495,-72.9045025
    ),
    DestinosData(
        "arequipa",
        "are_01",
        "Baños Termales de Yura",
        "aventura",
        R.drawable.yura,
        "Los baños termales de Yura se forman gracias a las aguas que provienen del interior del volcán Chachani, constituyendo una zona de verdadero relax que muchísimos turistas disfrutan por año. Y hay más beneficios naturales; por ejemplo, el clima se mantiene seco con temperaturas que rondan los 22 grados, y así gozaras de sol durante casi todos los días del año.",
        -16.2458375,-71.7304501
    ),
    DestinosData(
        "arequipa",
        "are_02",
        "Campiña de Arequipa",
        "aventura",
        R.drawable.campiniaarequipa,
        "La campiña arequipeña es un espectáculo de color verde: el valle enamora por su luz natural, andenes y colores vivos, todo con el imponente Misti de fondo. Varios miradores permiten apreciarla en todo su esplendor, pero tal vez donde se vive más su esencia es en el Molino de Sabandía, construido en 1621. Es un lugar de particular encanto, rodeado de árboles, cactus y diversas flores y plantas locales, donde se puede apreciar cómo se trituraba el trigo cuatro siglos atrás en sus grandes ruedas de piedra.",
        -16.4581575,-71.5218292
    ),
    DestinosData(
        "arequipa",
        "are_03",
        "Circuito de Playas de Camaná",
        "aventura",
        R.drawable.playascamana,
        "Naturaleza e historia se encuentran en Camaná. Magníficas playas que son escenario de aventuras y biodiversidad en todo su esplendor. Sobresalen la caleta de Quilca y los balnearios La Miel, Cerrillos, Ibiza y La Punta.",
        -16.660570, -72.646955
    ),
    DestinosData(
        "arequipa",
        "are_04",
        "Las cuevas de arte rupestre de Sumbay",
        "cultural",
        R.drawable.sumbay,
        "Los restos arqueológico de Sumbay están ubicadas en la Comunidad de Tambo Cañahuas, provincia y departamento de Arequipa. A 88 kilómetros de la ciudad de Arequipa. En la falda posterior del volcán Misti, a una altura de 4,127 a 5,100 m.s.n.m. Por el valor y significado que tienen las cuevas de Sumbay, donde importantes abrigos alojan pinturas rupestres de más de 8,000 años de antigüedad y que permiten acercarse las manifestaciones ancestrales de los cazadores de la edad de piedra, marcando la época pre-histórica de Arequipa.",
        -15.8896822,-71.6917652
    ),
    DestinosData(
        "arequipa",
        "are_05",
        "Reserva Nacional de Salinas y Aguada Blanca",
        "cultural",
        R.drawable.salinasyaguadablanca,
        "La Reserva Nacional de Salinas y Aguada Blanca se encuentra ubicada en las provincias de Arequipa y Caylloma en el departamento de Arequipa y en la provincia de General Sánchez Cerro del departamento de Moquegua. Su extensión es de 366,936 hectáreas. La altitud promedio es de 4,300 m.s.n.m. Su principal objetivo es conservar los recursos naturales y paisajísticos de la zona.",
        -16.2346456,-71.3584598
    ),
    DestinosData(
        "arequipa",
        "are_06",
        "Ruta del Sillar",
        "cultural",
        R.drawable.rutadelsillar,
        "La ruta del sillar es administrada por la Red de Cortadores de Sillar – Arequipa. Actualmente comprende una longitud de 2 mil metros, abarcando las canteras en explotación de Añashuayco, Cortadores y la cantera virgen de Culebrillas. Toma 40 minutos llegar desde Arequipa y el recorrido se realiza durante la mañana.",
        -16.3592155,-71.6123462
    ),
    DestinosData(
        "arequipa",
        "are_07",
        "Petroglifos de Toro Muerto",
        "cultural",
        R.drawable.toromuerto,
        "El Complejo Arqueológico Toro Muerto es un yacimiento de petroglifos creado por sociedades agroalfareras en el que plasmaron símbolos de su cosmovisión y escenas de la vida cotidiana en miles de rocas grabadas de origen volcánico. Representa un ejemplo sobresaliente de una singular tradición de arte rupestre.",
        -16.2235967,-72.5178916
    ),
    DestinosData(
        "ayacucho",
        "aya_01",
        "Bosque de Puyas de Raimondi",
        "aventura",
        R.drawable.puasraymondi,
        "A su paso por Áncash en pleno siglo XIX, el famoso naturalista italiano Antonio Raimondi se quedó admirado ante una planta de aspecto áspero que vio sobresalir sobre el páramo andino.",
        -13.5490707,-74.0522326
    ),
    DestinosData(
        "ayacucho",
        "aya_02",
        "Cataratas de Pumapaqcha, Batán y Qorimaqma",
        "aventura",
        R.drawable.pumapaqcha,
        "Cataratas míticas donde se hacen rituales a los dioses y espíritus andinos. La tradición popular cuenta que tienen una alta concentración de energía positiva y magnética. Están ubicadas en paralelo a la carretera que va a Cangallo.",
        -13.4511489,-74.213619
    ),
    DestinosData(
        "ayacucho",
        "aya_03",
        "Complejo arqueológico de Vilcashuamán",
        "cultural",
        R.drawable.vilcashuaman,
        "Vilcashuamán se convirtió en una verdadera obra de arte de la arquitectura inca. Cuenta con las típicas características de las construcciones levantadas por esta cultura: una plaza trapezoidal, templos dedicados al sol y la luna y un trono sagrado.",
        -13.6531234,-73.9582606
    ),
    DestinosData(
        "ayacucho",
        "aya_04",
        "Complejo arqueológico de Wari",
        "cultural",
        R.drawable.wari,
        "Corazón de una civilización prehispánica, el Complejo Arqueológico de Wari comprende las ruinas de una enorme ciudadela, de cuidadas y enormes murallas que alcanzaron hasta siete metros de altura. La importancia de esta ciudadela radica en que fue edificada con fines civiles, no religiosos, lo que la convierte en la primera ciudad de los Andes.",
        -13.891672,-75.7071313
    ),
    DestinosData(
        "ayacucho",
        "aya_05",
        "Cueva De Pikimachay",
        "cultural",
        R.drawable.cuevapikimachay,
        "La cueva de Pikimachay se sitúa al norte de Ayacucho a 2 850 msnm. y a 24 minutos de la ciudad. Es una cueva que tiene aproximadamente 24 m de ancho y doce de altura; esta al centro del cerro de Allqowillka, donde en 1969 el arqueologo norteamericano Richard Mc Neish, con el Proyecto Arqueologico Botanico Peabody, encontro restos con una antiguedad de 20 mil años. En esta cueva, se han encontrado instrumentos líticos del paleolítico andino y restos óseos de animales.",
        -13.0384816,-74.2308791
    ),
    DestinosData(
        "ayacucho",
        "aya_06",
        "Pueblo de Quinua",
        "cultural",
        R.drawable.puebloquinua,
        "Lleva su nombre debido a la queñua, un árbol típico que abunda en la zona. El Pueblo de la Quinua es un pueblo andino de hábiles artesanos, autores de los famosos retablos ayacuchanos, considerados un emblema nacional. Sus calles empedradas y casas con techos de tejas adornados con pintorescas iglesias de cerámica mantienen viva su herencia colonial.",
        -13.0814255,-74.2728652
    ),
    DestinosData(
        "cajamarca",
        "ca_01",
        "Complejo Arqueológico de Cumbemayo",
        "cultural",
        R.drawable.cumbemayo,
        "Descubierto en 1937, está ubicado a 20 km al suroeste de la ciudad de Cajamarca, es un paraje de singular belleza, formado por un escenario donde se conjugan el trabajo del hombre y la acción del tiempo, el complejo está rodeado por interesantes bosques de piedra que parecen reproducir las siluetas de piadosos frailes, conocidos como “Los Frailones”.",
        -7.1544913,-78.533899
    ),

    DestinosData(
        "cajamarca",
        "ca_02",
        "Complejo Turístico Baños del Inca",
        "cultural",
        R.drawable.baniodelinca,
        "Desde épocas preincaicas, los baños termales eran frecuentados por la élite local por ser un lugar para aliviar el cuerpo y la mente gracias a las propiedades curativas de sus aguas. Esta tradición fue heredada por el inca Atahualpa, quien se desplazaba hacia las aguas termales de “Pultumarka” después de los enfrentamientos de combate.",
        -7.1635354,-78.4669423
    ),

    DestinosData(
        "cajamarca",
        "ca_03",
        "Ventanillas de Combayo",
        "cultural",
        R.drawable.ventanillasdecombayo,
        "ste complejo funerario se caracteriza por presentar nichos en forma de ventanillas cuadrangulares y rectangulares, asentadas en la ladera del Cerro San Cristóbal de Cajamarca. Según los historiadores, fue construido y labrado por la cultura Cajamarca, al igual que el Complejo Arqueológico de Cumbemayo.",
        -7.0415938,-78.4004533
    ),
    DestinosData(
        "cusco",
        "cu_01",
        "Camino Inca a Machupicchu",
        "aventura",
        R.drawable.machupicchu,
        "En lo alto de la montaña, grandes e impresionantes bloques de piedra unidos entre sí sin amalgama alguna conforman uno de los centros religiosos, políticos y culturales más importantes del imperio incaico: Machu Picchu.",
        -13.1631412,-72.5471516
    ),
    DestinosData(
        "cusco",
        "cu_02",
        "Corredor Valle Sur",
        "aventura",
        R.drawable.vallesur,
        "Siguiendo el curso del río Urubamba, camino al altiplano, se encuentra un conjunto de diversos de poblados, centros arqueológicos y lugares naturales que forman el corredor Valle Sur. Una alternativa distinta para descubrir la ciudad de Cusco.",
        -13.5203787,-71.9757068
    ),
    DestinosData(
        "cusco",
        "cu_03",
        "Nevado del Ausangate",
        "extremo",
        R.drawable.ausangate,
        "Entre los Apus o montañas andinas, el Nevado Ausangate se levanta imponente y es admirado no solo por su extraordinaria belleza, sino respetado por lo que significa para la cosmovisión cusqueña: un nevado sagrado.",
        -13.7908329,-71.2412548
    ),
    DestinosData(
        "cusco",
        "cu_04",
        "Parque Arqueológico de Choquequirao",
        "cultural",
        R.drawable.choquequirao,
        "Bajo el seudónimo de la ‘hermana de Machupicchu’, Choquequirao tiene también una llamativa ciudadela de piedra, además de su propia historia que contar. Ubicada entre la colisión de dos mundos tan distintos, como son los Andes y la Amazonía peruana, está completamente rodeada de vegetación, lo que la hace un lugar de no tan sencillo acceso.",
        -13.3950071,-72.8744136
    ),
    DestinosData(
        "cusco",
        "cu_05",
        "Parque Arqueológico de Raqchi",
        "cultural",
        R.drawable.raqchi,
        "Según las últimas investigaciones realizadas, el mencionado parque arqueológico tiene un área aproximada de mil hectáreas, porque fuera de la gigantesca muralla inca que protege el parque, se encuentran también algunas construcciones como acueductos, tumbas subterráneas y recintos de la cultura pre-inca.",
        -14.1751201,-71.3725732
    ),
    DestinosData(
        "huancavelica",
        "huanc_01",
        "Circuito Azogue",
        "aventura",
        R.drawable.azogue,
        "Según el origen de Sacsamarca, fue fundado en el siglo XVI. Este hermoso lugar rodeado de grandes montañas resalta por su pintoresca iglesia y sus viviendas construidas a base de piedras y tejas andinas.",
        -12.8122982,-74.9760783
    ),

    DestinosData(
        "huancavelica",
        "huanc_02",
        "Circuito Rutas de los Espejos",
        "aventura",
        R.drawable.rutaespejos,
        "La Ruta de los Espejos la conforman seis lagunas de aguas cristalinas para apreciar la belleza del cielo andino reflejado en sus aguas, rodeadas de parajes increíbles, ricos en biodviersidad.",
        -13.3120147,-75.701234
    ),

    DestinosData(
        "huancavelica",
        "huanc_03",
        "Circuito Uchkus Inkañan",
        "aventura",
        R.drawable.uchkus,
        "Un circuito turístico, ubicado a 3800 m s. n. m., está lleno de bosques de piedras, monumentos arqueológicos y pobladores que mantienen vivas sus tradiciones y técnicas textiles ancestrales.",
        -12.7349783,-74.859781
    ),

    DestinosData(
        "huancavelica",
        "huanc_04",
        "Circuito Huaytará",
        "aventura",
        R.drawable.rutashuaytara,
        "La ciudad de Huaytará, capital de la provincia del mismo nombre, se encuentra ubicada en la vertiente del Pacífico de la cordillera occidental, a 2702 msnm, a 112 Km de la ciudad de Pisco, por la vía Los Libertadores rumbo a Ayacucho, y a 192 Km de la ciudad de Huancavelica",
        -13.6038098,-75.3549886
    ),
)