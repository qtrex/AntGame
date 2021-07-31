package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

public class StyledButton extends Button {
	public StyledButton () {
		getUnselectedStyle().setBgTransparency(255);
		getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		getAllStyles().setPadding(Component.TOP, 5);
		getAllStyles().setPadding(Component.BOTTOM, 5);
		getAllStyles().setPadding(Component.LEFT, 5);
		getAllStyles().setPadding(Component.RIGHT, 5);
	}
}
