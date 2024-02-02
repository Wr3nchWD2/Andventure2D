package Items;

import java.io.IOException;

import javax.imageio.ImageIO;;
public class OBJ_KEY extends OBJ{
	
	public OBJ_KEY(){
		
		name = "Gold Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Items/GoldKey_01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
