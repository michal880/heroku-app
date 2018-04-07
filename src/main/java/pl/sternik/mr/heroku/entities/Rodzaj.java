package pl.sternik.mr.heroku.entities;


public enum Rodzaj {
    
    DRAMAT("Dramat"),
    PRZYGODOWY("Przygodowy"),
    AKCJI("Akcji"),
    KOMEDIOWY("Komediowy");
    
    
    public static final Rodzaj[] ALL = { DRAMAT, PRZYGODOWY, AKCJI, KOMEDIOWY };
    
    
    private final String name;

    private Rodzaj(final String name) {
    	this.name = name;
    }
    
//    public static Status forName(final String name) {
//        if (name == null) {
//            throw new IllegalArgumentException("Nie mozna nula dla Status");
//        }
//        if (name.equalsIgnoreCase("NOWA")) {
//            return NOWA;
//        } else if (name.equalsIgnoreCase("DO_SPRZEDANIA")) {
//            return Status.DO_SPRZEDANIA;
//        }
//        throw new IllegalArgumentException("Nazwa \"" + name + "\" nie pasuje do zadengo Statusu");
//    }
//    
    
    public String getName() {
        return this.name;
    }
}
