package com.ggit.gui;

import com.ggit.simulation.Animal;
import com.ggit.simulation.IWorldMap;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
	private final IWorldMap map;

	MapPanel(IWorldMap map) {
		this.map = map;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawBackground(graphics);
		drawPlants(graphics);
		drawAnimals(graphics);
		drawBorder(graphics);
	}

	private Polygon drawLeaf(Graphics graphics) {
		int[] leafX = { 0, LayoutParams.MAP_SCALE / 4, LayoutParams.MAP_SCALE,
				3 * LayoutParams.MAP_SCALE / 4 };
		int[] leafY = { LayoutParams.MAP_SCALE, LayoutParams.MAP_SCALE / 4, 0,
				3 * LayoutParams.MAP_SCALE / 4 };
		graphics.setColor(CustomColors.plantColor);
		return new Polygon(leafX, leafY, leafX.length);
	}

	private void drawPlants(Graphics graphics) {
		Polygon leaf = drawLeaf(graphics);
		map.getPlantsLocations().forEach(position -> {
			leaf.translate(LayoutParams.MAP_SCALE * position.getX(),
					LayoutParams.MAP_SCALE * position.getY());
			graphics.fillPolygon(leaf);
			leaf.translate(-LayoutParams.MAP_SCALE * position.getX(),
					-LayoutParams.MAP_SCALE * position.getY());
		});
	}

	private void drawAnimals(Graphics graphics) {
		map.getAnimals().forEach((position, animals) -> {
			String animalsCount = Integer.toString(animals.size());
			int energy = animals.stream()
					.mapToInt(Animal::getEnergy)
					.max()
					.orElse(0);
			int energyColor = Math.min(energy / 10, CustomColors.energyColor.length - 1);
			graphics.setColor(CustomColors.energyColor[energyColor]);

			FontMetrics fm = graphics.getFontMetrics();
			int textWidth = (int) fm.getStringBounds(animalsCount, graphics).getWidth();
			int centerX = LayoutParams.MAP_SCALE * position.getX() + LayoutParams.MAP_SCALE / 2;
			int centerY = LayoutParams.MAP_SCALE * position.getY() + LayoutParams.MAP_SCALE / 2;
			graphics.fillOval(LayoutParams.MAP_SCALE * position.getX(), // draw animal
					LayoutParams.MAP_SCALE * position.getY(),
					LayoutParams.MAP_SCALE, LayoutParams.MAP_SCALE);

			graphics.setColor(new Color( // get the opposite color
					255 - graphics.getColor().getRed(),
					255 - graphics.getColor().getGreen(),
					255 - graphics.getColor().getBlue()));
			graphics.drawString(animalsCount, centerX - textWidth / 2,
					centerY + fm.getMaxAscent() / 2);
		});
	}

	private void drawBorder(Graphics graphics) {
		graphics.setColor(CustomColors.borderColor);
		graphics.drawRect(0, 0, LayoutParams.MAP_SCALE * map.getWidth(),
				LayoutParams.MAP_SCALE * map.getHeight());
		graphics.drawRect(1, 1, LayoutParams.MAP_SCALE * map.getWidth() - 1,
				LayoutParams.MAP_SCALE * map.getHeight() - 1);
	}

	private void drawBackground(Graphics graphics) {
		graphics.setColor(CustomColors.backgroundColor);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(CustomColors.mapBackgroundColor);
		graphics.fillRect(0, 0, LayoutParams.MAP_SCALE * map.getWidth(),
				LayoutParams.MAP_SCALE * map.getHeight());
	}
}
