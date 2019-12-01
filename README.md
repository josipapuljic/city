Potrebno je kreirati API servis koji će služiti kao open database za gradove. Aplikacija nema HTML dio već sva komunikacija ide preko JSON-a.

Kreirati kao defaultne podatke 10 gradova. Svaki grad treba imati ime, opis grada i broj stanovnika.
API rute:
- kreiranje korisnika - korisnik treba poslati email i password, a aplikacija mu treba izgenerirati token s kojim će komunicirati na ostalim dijelovima aplikacije koji zahtijevaju autentifikaciju
- login korisnika

Rute za autentificirane korisnike:
- kreiranje novih gradova - korisnik treba poslati ime, opis grada i broj stanovnika (obavezna polja)
- dodavanje gradova u listu najdražih gradova
- brisanje grada iz liste najdražih gradova

Rute za neautentificirane korisnike:
- izlistavanje svih gradova
- sortirani defaultno po datumu kreiranja
- sortirani po broju korisnika koji su ih naveli kao najdraže (izvest ovo na optimalan način)

Tehnologija koja se treba koristiti za izradu ovog zadatka je Spring Framework. Za rute je potrebno napisati testove. Dozvoljeno je korištenje dodatnih libraryja koji bi mogli pomoći u realizaciji zadatka.

Aplikaciju trebaju moći konzumirati razni klijenti, poput Android/iOS aplikacija, i trebaju znati koje su mogućnosti aplikacije.
