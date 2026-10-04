public class OggettoStats{
	//stati
	//private String nome;
    private int attacco;
    private int difesa;
    private int curaHp;
	private String descrizioneAbilita;
	
	//costruttore
	public OggettoStats(int attacco, int difesa, int curaHP, String descrizioneAbilita) {
        this.attacco = attacco;
        this.difesa = difesa;
        this.curaHp = curaHP;
        this.descrizioneAbilita = descrizioneAbilita;
    }
	
	//metodi
	// Getter per stampare le statistiche
    @Override
    public String toString() {
        return "[ATK: " + attacco + ", DEF: " + difesa + ", Cura: " + curaHp + " | Abilità: " + descrizioneAbilita + "]";
    }
	
	//public String getNome() { return this.nome; }
    public int getAttacco(){ return this.attacco; }
    public int getDifesa(){ return this.difesa; }
    public int getCuraHp(){ return this.curaHp; }
    public String getDescrizioneAbilita(){ return this.descrizioneAbilita; }
}