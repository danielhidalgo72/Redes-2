import java.io.Serializable;
public class Juego implements Serializable{
	private int modo;
	private int categoria;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	
	public void setModo(int modo){
		this.modo=modo;
	}
	
	public int getModo(){
		return modo;
	}
	
	public void setCategoria(int categoria){
		this.categoria=categoria;
	}
	
	public int getCategoria(){
		return categoria;
	}
	
	public void setX1(int x){
		x1=x;
	}
	
	public int getX1(){
		return x1;
	}
	
	public void setY1(int y){
		y1=y;
	}
	
	public int getY1(){
		return y1;
	}
	
	public void setX2(int x){
		x2=x;
	}
	
	public int getX2(){
		return x2;
	}
	
	public void setY2(int y){
		y2=y;
	}
	
	public int getY2(){
		return y2;
	}
}
