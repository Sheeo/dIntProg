BouncingBalls. Aflevering i dIntProg Q1 2010 uge 4 af Michael Søndergaard og
Mathias Rav.

OVERBLIK

Den i opgaven givne Vector-klasse er udvidet betydeligt. Koordinater gemmes som
doubles i stedet for ints, da det giver aktørene mulighed for bevægelse med
mindre end 1 pixel pr. act(). Man kan oprette en vektor enten ved kartesiske
eller polære koordinater, og der sker ingen omregning før de relevante gettere
kaldes. Se Vector.java for det fulde metodekatalog.

De vigtigste klasser er verdensklasserne AutoPoolBreak og BallWorldDriver (prøv
dem!) samt aktøren Ball. Alle nedarver fra nogle grundlæggende klasser
(PhysicsWorld, BallWorld, ShapeActor, DynamicActor), som er abstraktioner oven
på Greenfoots World og Actor. Abstraktionerne gør det nemmere at lave en simpel
fysisk simulation som vores.

Grundlæggende har vi ShapeActor extends Actor, som er en Actor med nogle
metoder til håndtering af position og størrelse som vektorer og en feltvariabel
af typen Shape. (Dette kan virke som en unødig abstraktion, når vi kun arbejder
med cirkler, men baseklassen Shape udvides med Rectangle og RoundedRectangle i
uge 5.)

ShapeActor er som udgangspunkt en statisk aktør: Den bevæger sig ikke og er
ikke i stand til at håndtere kollisioner med andre aktører. ShapeActor
forventes heller ikke at implementere act().

Til dette formål findes dog DynamicActor extends ShapeActor, som har en
hastighed og nogle metoder til at håndtere kollisioner med ShapeActors og
verdens fire vægge. Heller ikke en DynamicActor bør foretage kollisions-
detektion og bevægelse i act(); i stedet skal den implementere
collidedWithWall(), handleIntersection() og move(), som bliver kaldt af
PhysicsWorld.act().

En Ball er en DynamicActor, hvis shape er en Circle, og som implementerer
elastisk stød med andre Balls og med væggene.
