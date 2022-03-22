# fotoalbum
Appen skal vise informasjon om brukere, deres fotoalbum og foto i albumene. Informasjonen skal lastes ned fra:

https://jsonplaceholder.typicode.com (Lenker til en ekstern side.)

Serveren leverer ulike testdata på JSON-format. Appen skal bruke følgende ressurser:

/users
/albums
/photos

Appen starter med å vise en liste med brukere (fra /users). Disse presenteres i en liste som ligger i et fragment. Ved klikk på en av brukerne skal brukerens album (/albums) vises i en liste i et annet fragment  (dvs. du må hente alle album for valgt bruker). Ved klikk på album vises en liste med alle bilder (/photos) i valgt album i et tredje fragment. For hvert bilde vises thumbnail og tittel. Klikk på et bilde viser bildet (gitt av url) i full størrelse i et fjerde fragment. Brukeren skal kunne endre tittel på et bilde (photo). Det skal også være mulig å slette et bildet. Merk: jsonplaceholder-APIet har støtte for endring og sletting men det skjer ingen faktisk endring i databasen. Vi kan alikevel utføre slike kommandoer og få respons fra server som indikerer at endring og/eller sletting er utført.

Appen skal lages vha. en aktivitet bestående av flere fragmenter sammen med navigation.

Noen tips:

Lag modellklasser som representerer de data som lastes ned (User, Album, Photo m.fl.). Ser du på data du får fra /users ser du at hvert user-objekt, i tillegg til enkeltverdier, inneholder et Address- og et Company-objekt. Address-objektet inneholder igjen et Geo-objekt. Du må derfor lage følgende modellklasser med attributter tilsvarende det du får fra /users: User, Address, Company, Geo, Photo, Album. Du kan dermed laste ned og konvertere til tilsvarende Kotlin-objekter. 

Følgende momenter skal med i løsninga:

Appen utvikles vha. en Activity med flere fragmenter.
Navigation, SafeArgs
Toolbar. Tilbakenavigering.
ViewModul, LiveData, ev. LiveData Transformations.
Data-Binding
Retrofit til nedlasting av data.
Moshi til json-konvertering.
Glide til visning av thumbnails og bilder.
RecyclerView til listene i de ulike fragmentene.
Appen skal fungere uavbrutt selv om brukere roterer skjermen frem/tilbake mens nedlasting pågår. 
Følgende kan også være aktuelt:

Ev. bruk av ViewModelProvider.Factory (for å kunne sende parametre inn til ViewModel-objektet)
Ev. bruk av sidemeny (Navigation Drawer) og/eller bunnmeny (bottom menu)
m.m.
For å ha oversikt over alle disse elementene bør du minimum ha fullført alle laboppgaver (inkl. teori) frem til og med modul 8.

Tips, Glide:

Hvis du ikke setter "User-Agent" som header vil ikke URLene fungere, bruk derfor følgende:

val url: GlideUrl = GlideUrl(photo.getThumbnailUrl(), new LazyHeaders.Builder()
    .addHeader("User-Agent", "android")
    .build());
Glide.with(myViewHolder.itemView.getContext())
     .load(url)
     .into(thumbView);
