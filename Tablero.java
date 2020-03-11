import java.io.Serializable;
import  java.util. *; 
/**
 *
 * @author usuario
 */
public class Tablero implements Serializable {
    
    //ArrayList<String> animales=new ArrayList<String>();
	//ArrayList<String> categoria;
	private char alfabeto[ ] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private String animales[ ] = {"PERRO","GATO","CONEJO","PEZ","VACA","GALLO","SAPO","DELFIN","TIGRE","LEON","CEBRA","PANDA","KOALA","RATON"};
    private String paises[ ] = {"MEXICO","COLOMBIA","PERU","RUSIA","JAPON","CHINA","COREA","FRANCIA","BRASIL","CHILE","EGIPTO","EUA","CANADA","ECUADOR"};
	private String generos[ ] = {"POP","ROCK","MERENGUE","SALSA","CUMBIA","RAP","CLASICA","INDIE","PUNK","BLUES","JAZZ","HIPHOP","METAL","SKA"};
	private String flores[ ] = {"CLAVEL","ROSA","TULIPAN","DALIA","LIRIO","ORQUIDEA","GIRASOL","LAVANDA","NARCISO","POENIA","AMAPOLA","JAZMIN","AZUCENA","PETUNIA"};
	private char[][] sopa = new char[16][16];
	private int[] yaDescubiertas = new int[15];
	private String[] categoria = new String[15];
	private String[] anagrama = new String[15];
    private static Random random = new Random(new Date().getTime());
	
    public Tablero(int modo, int categoria) {
        //poblarArrays();
		switch (categoria) {
            case 1:
				this.categoria=animales;
                break;
            case 2:
			    this.categoria=paises;
                break;
            case 3:
                this.categoria=generos;
                break;
            case 4:
				this.categoria=flores;
                break;
        }
		/*if(modo==2){
			anagrama=anagrama(this.categoria);
			categoria= new String[15];
			categoria=anagrama;
		}*/
		inicializarTablero();
        generarTablero();
    }
	
	public void inicializarTablero(){
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++){
			sopa[i][j]=' ';
			}
	}
	
	/*public String[] anagrama(String[] array){
		String[] nArray = new String[15];
		
	}*/
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (char i = 0; i < 15; i++) {
            sb.append(String.format(" %2d", i + 1));
        }
        sb.append("\n");
        
        for (int i = 0; i < 15; i++) {
            sb.append(String.format(" %2d", i + 1));
            for (int j = 0; j < 15; j++) {
                sb.append("  "+sopa[i][j]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
	
	public void relojInsertar(String palabra){
		int x=0, y=0;
		boolean	band=false, casillaValida=false, lineaValida=true, bandEscribe=false;
		while(!band){
			while(!casillaValida){
				x=random.nextInt(15);
				y=random.nextInt(15);
				if(sopa[x][y]==' ' || sopa[x][y]==palabra.charAt(0))
					casillaValida=true;
			}
			//este o reloj en 3:00
			if(x+palabra.length()<15 && band==false){
				System.out.println("este");
				for(int i=x;i<x+palabra.length();i++){
					if(sopa[i][y]!=' ' && sopa[i][y]!=palabra.charAt(i-x)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
				}
				if(bandEscribe){
					for(int i=x;i<x+palabra.length();i++){
						sopa[i][y]=palabra.charAt(i-x);
					}
					band=true;
				}
			}
			//sureste o reloj en 4:30
			if(x+palabra.length()<15 && y+palabra.length()<15 && band==false){
				System.out.println("sureste");
				for(int i=x, j=y;i<=x+palabra.length();i++){
					if(sopa[i][j]!=' ' && sopa[i][j]!=palabra.charAt(i-x)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
					j++;
				}
				if(bandEscribe){
					for(int i=x, j=y;i<x+palabra.length();i++){
						sopa[i][j]=palabra.charAt(i-x);
						j++;
					}
					band=true;
				}
			}
			//sur o reloj en 6:00
			if(y+palabra.length()<15 && band==false){
				System.out.println("sur");
				for(int j=y;j<y+palabra.length();j++){
					if(sopa[x][j]!=' ' && sopa[x][j]!=palabra.charAt(j-y)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
				}
				if(bandEscribe){
					for(int j=y;j<y+palabra.length();j++){
						sopa[x][j]=palabra.charAt(j-y);
					}
					band=true;
				}
			}
			//suroeste o reloj en 7:30
			if(x-palabra.length()>=0 && y+palabra.length()<15 && band==false){
				System.out.println("suroeste");
				for(int i=x, j=y;j<y+palabra.length();j++){
					if(sopa[i][j]!=' ' && sopa[i][j]!=palabra.charAt(j-y)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
					i--;
				}
				if(bandEscribe){
					for(int i=x, j=y;j<y+palabra.length();j++){
						sopa[i][y]=palabra.charAt(j-y);
						i--;
					}
					band=true;
				}
			}
			//oeste o reloj en 9:00
			if(x-palabra.length()>=0 && band==false){
				System.out.println("oeste");
				for(int i=x;i>x-palabra.length();i--){
					if(sopa[i][y]!=' ' && sopa[i][y]!=palabra.charAt(x-i)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
				}
				if(bandEscribe){
					for(int i=x;i>x-palabra.length();i--){
						sopa[i][y]=palabra.charAt(x-i);
					}
					band=true;
				}
			}
			//noroeste o reloj en 10:30
			if(x-palabra.length()>=0 && y-palabra.length()>=0 && band==false){
				System.out.println("noroeste");
				for(int i=x, j=y;j>y-palabra.length();j--){
					if(sopa[i][j]!=' ' && sopa[i][j]!=palabra.charAt(y-j)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
					i--;
				}
				if(bandEscribe){
					for(int i=x, j=y;j>y-palabra.length();j--){
						sopa[i][y]=palabra.charAt(y-j);
						i--;
					}
					band=true;
				}
			}
			//norte o reloj en 12:00
			if(y-palabra.length()>=0 && band==false){
				System.out.println("norte");
				for(int j=y;j>y-palabra.length();j--){
					if(sopa[x][j]!=' ' && sopa[x][j]!=palabra.charAt(y-j)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
				}
				if(bandEscribe){
					for(int j=y;j>y-palabra.length();j--){
						sopa[x][j]=palabra.charAt(y-j);
					}
					band=true;
				}
			}
			//noreste o reloj en 1:30
			if(x+palabra.length()<15 && y-palabra.length()>=0 && band==false){
				System.out.println("noreste");
				for(int i=x, j=y;i<x+palabra.length();i++){
					if(sopa[i][j]!=' ' && sopa[i][j]!=palabra.charAt(i-x)){
						bandEscribe=false;
						break;
					}
					else
						bandEscribe=true;
					j--;
				}
				if(bandEscribe){
					for(int i=x, j=y;i<x+palabra.length();i++){
						sopa[i][j]=palabra.charAt(i-x);
						j--;
					}
					band=true;
				}
			}
		}
	}
	
	public boolean relojBuscar(int x1, int y1, int x2, int y2){
		boolean band=false;
		String palabra="";
		//este reloj en 3:00
		if(x1<x2 && y1==y2 && band==false){
			for(int i=x1;i<x2;i++){
				palabra=palabra+String.valueOf(sopa[i][y1]);
			}
			for(int i=0;i<13;i++){
				if(categoria[i].equals(palabra)){
					band=true;
					break;
				}
			}
		}
		//sureste reloj en 4:30
		if(x1<x2 && y1==y2 && band==false){
			for(int i=x1;i<x2;i++){
				palabra=palabra+String.valueOf(sopa[i][y1]);
			}
			for(int i=0;i<13;i++){
				if(categoria[i].equals(palabra)){
					yaDescubiertas[i]=1;
					band=true;
					break;
				}
			}
		}
		return band;
	}
    
    private void generarTablero() {
        for (int i = 0; i <13; i++) {
            relojInsertar(categoria[i]);
        }
    }
    
}