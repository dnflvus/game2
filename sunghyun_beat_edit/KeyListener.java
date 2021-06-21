package sunghyun_beat_edit;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(SungHyun_Beat.game == null) 
			return;
		if(e.getKeyCode() == KeyEvent.VK_S) {
			SungHyun_Beat.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			SungHyun_Beat.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			SungHyun_Beat.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			SungHyun_Beat.game.pressSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			SungHyun_Beat.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			SungHyun_Beat.game.pressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			SungHyun_Beat.game.pressL();
		}
	}
	public void keyReleased(KeyEvent e) {
		if(SungHyun_Beat.game == null) 
			return;
		if(e.getKeyCode() == KeyEvent.VK_S) {
			SungHyun_Beat.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			SungHyun_Beat.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			SungHyun_Beat.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			SungHyun_Beat.game.releaseSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			SungHyun_Beat.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			SungHyun_Beat.game.releaseK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			SungHyun_Beat.game.releaseL();
		}
	}
}
	