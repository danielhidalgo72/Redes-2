import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Tablero implements Serializable {
    
    private char alfabeto[ ] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private String animales[ ] = {"PERRO","GATO","CONEJO","PEZ","VACA","GALLO","SAPO","DELFIN","TIGRE","LEON","CEBRA","PANDA","KOALA","RATON"};
    private String paises[ ] = {"MEXICO","COLOMBIA","PERU","RUSIA","JAPON","CHINA","COREA","FRANCIA","BRASIL","CHILE","EGIPTO","EUA","CANADA","ECUADOR"};
	private String generos[ ] = {"POP","ROCK","MERENGUE","SALSA","CUMBIA","RAP","CLASICA","INDIE","PUNK","BLUES","JAZZ","HIPHOP","METAL","SKA"};
	private String flores[ ] = {"CLAVEL","ROSA","TULIPAN","DALIA","LIRIO","ORQUIDEA","GIRASOL","LAVANDA","NARCISO","POENIA","AMAPOLA","JAZMIN","AZUCENA","PETUNIA"};
	private char[][] sopa = new char[16][16];
	private char[] yaDescubiertas = new char[15];
	private String[] categoria = new String[15];
	private String[] anagrama = new String[15];
    private static Random random = new Random(new Date().getTime());
	private int estadoJuego;
    
    public Tablero(int modo, int categoria) {
        
		switch (categoria) {
            case 1:
                categoria=animales;
                break;
            case 2:
                categoria=paises;
                break;
            case 3:
                categoria=generos;
                break;
            case 4:
                categoria=flores;
                break;
        }
		if(modo==2){
			anagrama=anagrama(this.categoria);
			categoria= new String[15];
			categoria=anagrama;
		}
        estadoJuego = 0;
        generarTablero();
    }
	
	public String[] anagrama(String[] array){
		String[] nArray = new String[15];
		
	}
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (char i = 0; i < 15; i++) {
            sb.append(String.format(" %2d ", i + 1));
        }
        sb.append("\n");
        
        for (int i = 0; i < 15; i++) {
            sb.append(String.format(" %2d ", i + 1));
            for (int j = 0; j < 15; j++) {
                sb.append(sopa[i][j]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    /**
     * @return the estadoJuego
     */
    public int getEstadoJuego() {
        return estadoJuego;
    }

    /**
     * @param estadoJuego the estadoJuego to set
     */
    public void setEstadoJuego(int estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
    
    
    private void procesaTablero() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int n = 0;
                if (tablero[i][j] >= 0) {
                    if (i > 0) {
                        tablero[i][j] += tablero[i - 1][j] == -1 ? 1 : 0;
                    }
                    if (j > 0) {
                        tablero[i][j] += tablero[i][j - 1] == -1 ? 1 : 0;
                    }
                    if (j != y - 1) {
                        tablero[i][j] += tablero[i][j + 1] == -1 ? 1 : 0;
                    }
                    if (i != x - 1) {
                        tablero[i][j] += tablero[i + 1][j] == -1 ? 1 : 0;
                    }
                    if (i > 0 && j > 0) {
                        tablero[i][j] += tablero[i - 1][j - 1] == -1 ? 1 : 0;
                    }
                    if (j != y - 1 && i != x - 1) {
                        tablero[i][j] += tablero[i + 1][j + 1] == -1 ? 1 : 0;
                    }
                    if (j != y - 1 && i > 0) {
                        tablero[i][j] += tablero[i - 1][j + 1] == -1 ? 1 : 0;
                    }
                    if (i != x - 1 && j > 0) {
                        tablero[i][j] += tablero[i + 1][j - 1] == -1 ? 1 : 0;
                    }
                }
            }
        }
    }
    
    public void hazJugada(int x, int y, int t) {
        if (t == 0) // Tiro
        {
            if (tablero[x][y] == -1) {
                for (int i = 0; i < x; i++) {
                    Arrays.fill(visibles[i], true);
                }
                estadoJuego = -1;
            } else {
                destapa(x, y);
                if (minas + descubiertas + marcadas == x * y) {
                    for (int i = 0; i < x; i++) {
                        Arrays.fill(visibles[i], true);
                    }
                    estadoJuego = 1;
                }
            }
        } else {
            marcas[x][y] = !marcas[x][y];
            if (tablero[x][y] == -1) {
                minas += marcas[x][y] ? -1 : 1;
                marcadas += marcas[x][y] ? 1 : -1;
            }
            if (minas == 0) {
                estadoJuego = 1;
            }
        }
    }
}