
package espcraft.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import espcraft.EspcraftModVariables;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class EspguinormalGuiWindow extends ContainerScreen<EspguinormalGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = EspguinormalGui.guistate;
	TextFieldWidget name;
	TextFieldWidget channel;
	public EspguinormalGuiWindow(EspguinormalGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 275;
		this.ySize = 200;
	}
	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/espguinormal.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		name.render(ms, mouseX, mouseY, partialTicks);
		channel.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (name.isFocused())
			return name.keyPressed(key, b, c);
		if (channel.isFocused())
			return channel.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		name.tick();
		channel.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "ESPcraft " + (EspcraftModVariables.MapVariables.get(world).version) + "", 189, 184, -12829636);
		this.font.drawString(ms, "Mode: " + (new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Receiver")) + " / " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Name")) + " / " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Channel")) + "", 6, 184, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 210, this.guiTop + 13, 55, 20, new StringTextComponent("MC -> NET"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalGui.ButtonPressedMessage(0, x, y, z));
				EspguinormalGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 210, this.guiTop + 38, 55, 20, new StringTextComponent("MC <- NET"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalGui.ButtonPressedMessage(1, x, y, z));
				EspguinormalGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		name = new TextFieldWidget(this.font, this.guiLeft + 10, this.guiTop + 158, 60, 20, new StringTextComponent("UUID")) {
			{
				setSuggestion("UUID");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("UUID");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("UUID");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:name", name);
		name.setMaxStringLength(32767);
		this.children.add(this.name);
		channel = new TextFieldWidget(this.font, this.guiLeft + 75, this.guiTop + 158, 60, 20, new StringTextComponent("Channel")) {
			{
				setSuggestion("Channel");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Channel");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Channel");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:channel", channel);
		channel.setMaxStringLength(32767);
		this.children.add(this.channel);
		this.addButton(new Button(this.guiLeft + 140, this.guiTop + 158, 40, 20, new StringTextComponent("Set"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalGui.ButtonPressedMessage(2, x, y, z));
				EspguinormalGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
