package com.idnp.danp_proyecto_final.data

import com.idnp.danp_proyecto_final.R

data class DepartamentosData (
    val code:String,
    val title:String,
    val imgUri:Int,
    val description: String,
    //val destino: List<DestinosData>,

    /**
     * Departamentos / Destinos List
     * */
)
val departamentosList: List<DepartamentosData> = listOf(
    DepartamentosData(
        "amazonas",
        "Amazonas",
        R.drawable.amazonas,
        "Bosques de neblina y páramos con privilegiados microclimas te dan la bienvenida a un departamento donde la historia y la naturaleza están en su máxima expresión. Descubre en Amazonas imponentes paisajes, una flora y fauna envidiable y encuentra en cada una de sus estructuras arquitectónicas, como la enigmÃ¡tica ciudadela de Kuélap, y sus misteriosas tumbas y sarcófagos, un relato diferente.",
    ),
    DepartamentosData(
        "ancash",
        "Áncash",
        R.drawable.ancash,
        "En Áncash encontrarás nevados, lagunas, valles y playas que te impresionarán. Aventúrate a probar los circuitos de trekking (caminata), recorre su geografía en bicicleta de montaña, disfruta de la naturaleza, hermosas lagunas y nevados que cautivarán tus sentidos. Una infinidad de posibilidades te esperan. Disfruta de sus fiestas y mira cómo se llenan de color las calles de esta ciudad que recoge la tradición y el presente de sus habitantes.",
    ),
    DepartamentosData(
        "apurimac",
        "Apurímac",
        R.drawable.apurimac,
        "Hacer parapente en Sóndor, canotaje en el río Pachachaca y downhill en bicicleta son algunas de las actividades que podrías disfrutar durante tu estadía en la región de Apurímac, considerada como el perfecto escenario de los deportes de aventura. Y si lo tuyo es relajarte, no te preocupes, que los baños termales como Cconoc y sitios arqueológicos como el Conjunto Arqueológico de Saywite y el Complejo Arqueológico Sóndor te darán una experiencia única. Definitivamente, Apurímac es un lugar que no puedes dejar de visitar.",
    ),
    DepartamentosData(
        "arequipa",
        "Arequipa",
        R.drawable.arequipa,
        "Conocida como la Ciudad Blanca por su arquitectura esculpida en sillar, Arequipa resplandece favorecida por un clima siempre soleado todo el año y un cielo azul que te invitan a caminar y admirar la belleza de sus monumentos, nevados, volcanes y profundos cañones, además de disfrutar de su exquisita gastronomía y una vida nocturna que te animarán a regresar más de una vez.",
    ),
    DepartamentosData(
        "ayacucho",
        "Ayacucho",
        R.drawable.ayacucho,
        "Ayacucho es naturaleza y fiesta. Recorre sus casonas e iglesias coloniales como la Catedral y el templo de Santo Domingo; disfruta de sus fiestas llenas de color y diviértete en Semana Santa con un pueblo arraigado en su fe. Conoce la tradición alfarera de la mano de sus artesanos y deja que sus paisajes de desbordada naturaleza viva te desconecten. Ayacucho, un lugar que merece ser recorrido en su totalidad, te espera.",
    ),
    DepartamentosData(
        "cajamarca",
        "Cajamarca",
        R.drawable.cajamarca,
        "Cajamarca es una tierra donde la historia se une a la naturaleza para crear una experiencia inolvidable. Encuentra en esta región una magnífica arquitectura colonial, pueblos coloridos y una gastronomía digna de admirar. Relájate en los Baños del Inca, admira el Complejo Arqueológico de Cumbemayo y diviértete en sus carnavales llenos de vida y color.",
    ),
    DepartamentosData(
        "cusco",
        "Cusco",
        R.drawable.cusco,
        "No existe una forma sencilla de explicar la majestuosidad que representa el Cusco. Esta región reúne historia, modernidad y aventura con una mística que envuelve desde la plaza de armas cusqueña hasta los lugares más alejados de ella. Llénate de energía en Moray, descubre la impresionante arquitectura de Ollantaytambo y Pisac y siéntete un poco más cerca del cielo en la ciudadela inca de Machu Picchu.",
    ),
    DepartamentosData(
        "huancavelica",
        "Huancavelica",
        R.drawable.huancavelica,
        "El corazón de los andes se encuentra dominado por dos grandes culturas: Wari y Chancas. Transformada luego en centro militar por los Incas y convertida en ciudad española para la explotación de la plata y el mercurio, Huancavelica tiene muchos rostros y los muestra con orgullo en los sitios arqueológicos, templos y casonas del casco urbano.",
    ),
    DepartamentosData(
        "huanuco",
        "Huánuco",
        R.drawable.huanuco,
        "Huánuco te muestra la unión de la sierra y la selva peruana. Visita sus elevadas montañas, aguas termales, cuevas, lagunas y una seductora formación rocosa llamada la Bella Durmiente; llénate de historia con sus monumentos como el Templo de la Manos Cruzadas y aventúrate a conocer la naturaleza viva en la Cueva de las Lechuzas de Tingo Marí\u00ADa.",
    ),
    DepartamentosData(
        "ica",
        "Ica",
        R.drawable.ica,
        "A solo unas horas de Lima se encuentra la mezcla perfecta de aventura y relax. Ica es más que solo un buen pisco: disfruta de sus playas, valles y del increíble oasis de la Huacachina todo el año y atrévete a realizar sandboard sobre las dunas o recorre los inmensos desiertos en un tubular.",
    ),
    DepartamentosData(
        "junin",
        "Junín",
        R.drawable.junin,
        "Cataratas y valles con paisajes en su estado más puro, pueblos con profunda vocación religiosa y una reserva nacional con animales del ande son algunas de las muchas características que envuelven a Junín y la convierten en una región llena de sorpresas. Atrévete a ir más allá ¡ y realiza deportes de aventura como el canotaje en el río Chanchamayo o trekking en el nevado de Huaytapallana. ",
    ),
    DepartamentosData(
        "libertad",
        "La Libertad",
        R.drawable.libertad,
        "Visita la ciudad de barro de Chan Chan, templos, pirámides y huacas. Vive una experiencia inolvidable en La Libertad por Semana Santa.",
    ),
    DepartamentosData(
        "lambayeque",
        "Lambayeque",
        R.drawable.lambayeque,
        "Lambayeque es una región pintoresca que tiene mucho por ofrecer: podrás conocer la cultura de este pueblo no solo por sus monumentos históricos como el Señor de Sipán, sus valles y pirámides, sino también por su exquisita gastronomía y la vida nocturna de su gente",
    ),
    DepartamentosData(
        "lima",
        "Lima",
        R.drawable.lima,
        "Recorrer las calles de Lima es admirar sus iglesias, sitios arqueológicos y casonas con balcones que conviven con modernos edificios. Lima también ofrece una gran variedad de espectáculos culturales y es reconocida en el mundo por ser la Capital Gastronómica de Latinoamérica. Su cocina que se ha nutrido de tradiciones andinas, europeas, africanas y orientales, y en sus restaurantes se pueden disfrutar las cocinas regionales del Perú.",
    ),
    DepartamentosData(
        "loreto",
        "Loreto",
        R.drawable.loreto,
        "Loreto es una verdadera oportunidad para desconectarte del mundo. Paisajes exóticos y una fauna y flora rica en variedad te envolverán desde que empieces tu recorrido. Conoce sus bosques sobre arena blanca, sé parte de la tribu de los Boras por un día y recorre parte del río Amazonas de la mano de los que mejor la conocen: su gente.",
    ),
    DepartamentosData(
        "madre",
        "Madre de Dios",
        R.drawable.madredios,
        "Madre de Dios alberga bosques infinitos, ríos sinuosos y abundante vida natural. Es reserva de flora y fauna, así como, refugio de especies en peligro de extinción como el lobo de crin y el ciervo de los pantanos.",
    ),
    DepartamentosData(
        "moquegua",
        "Moquegua",
        R.drawable.oquegua,
        "Moquegua es el intermedio perfecto entre mar y campiña. Quebradas, formaciones rocosas y desiertos te dan la bienvenida a una región donde la buena gastronomía se hace presente. Ven y disfruta de los pueblos pintorescos, casonas, exclusivos vinos y piscos de una ciudad que tiene mucho por contar.",
    ),
    DepartamentosData(
        "pasco",
        "Pasco",
        R.drawable.pasco,
        "Pasco es un lugar donde los contrastes son la base de todo: sierra y selva se unen para crear una cadena de frías montañas y mucha vegetación que te permitirán realizar desde deportes de aventura en los circuitos del Bosque de Piedras de Huayllay, relajarte en los baños termales de la Calera, hasta las cataratas que le dan pase a la exuberante selva central.",
    ),
    DepartamentosData(
        "piura",
        "Piura",
        R.drawable.piura,
        "En Piura, la tierra del verano eterno, encuentras balnearios y playas que son las favoritas de los surfistas: Máncora, Colán, Los Órganos, Vichayito, Cabo Blanco, Lobitos.",
    ),
    DepartamentosData(
        "puno",
        "Puno",
        R.drawable.puno,
        "En Puno podrás ser parte de una atmósfera mágica donde la leyenda, las tradiciones y las fiestas multicolor se respiran todos los días. Sus sorprendentes islas flotantes como los Uros, fabricadas con esteras de totoras, solo son superadas por el místico Lago Titicaca.",
    ),
    DepartamentosData(
        "martin",
        "San Martín",
        R.drawable.martin,
        "San Martín es una región donde puedes reencontrarte con la naturaleza y dejar de lado el trajín diario. Conoce sus cataratas naturales y baños termales con cualidades medicinales.",
    ),
    DepartamentosData(
        "tacna",
        "Tacna",
        R.drawable.tacna,
        "Tacna es una región singular: desiertos y valles fértiles conviven para crear paisajes únicos. El clima cálido de las mañanas te permitirá disfrutar de sus playas con oleajes tranquilos y sus aguas termales y lagunas serán los lugares perfectos para que te puedas relajar.",
    ),
    DepartamentosData(
        "tumbes",
        "Tumbes",
        R.drawable.tumbes,
        "Nada mejor que empezar el día en la arena de grano fino de Punta Sal o caminando entre esteros y manglares. Tumbes es el lugar indicado para los que buscan relajarse y conectarse con la naturaleza.",
    ),
    DepartamentosData(
        "ucayali",
        "Ucayali",
        R.drawable.ucayali,
        "Con impactantes lagos, cataratas y una flora y fauna envidiables, Ucayali te invita a vivir una experiencia diferente con la naturaleza y sus raíces profundas. Conoce las costumbres de las etnias nativas que viven en la Amazonía y sé parte de sus tradiciones.",
    )

)