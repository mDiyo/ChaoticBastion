package bastion.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;
import bastion.CContent;
import bastion.inventory.InventoryItem;
import bastion.util.IItemSucker;

public class Basket extends Item implements IItemSucker
{
    public Basket(int par1)
    {
        super(par1);
        this.setCreativeTab(CContent.tab);
    }

    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) //Eject inventory
    {
        InventoryItem inventory = new InventoryItem(stack, 9);
        for (int id = 0; id < 9; id++)
        {
            ItemStack invStack = inventory.getStackInSlot(id);
            if (invStack != null)
            {
                if (!player.inventory.addItemStackToInventory(invStack))
                    spawnItemAtPlayer(player, invStack);
            }
        }
        inventory.clearInventory();
        inventory.save();
        return stack;
    }

    public static void spawnItemAtPlayer (EntityPlayer player, ItemStack stack)
    {
        if (!player.worldObj.isRemote)
        {
            EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, stack);
            player.worldObj.spawnEntityInWorld(entityitem);
            if (!(player instanceof FakePlayer))
                entityitem.onCollideWithPlayer(player);
        }
    }

    @Override
    public void addWorldItems (EntityItem target, ItemStack itemstack)
    {
        InventoryItem inventory = new InventoryItem(itemstack, 9);
        inventory.addItemStackToInventory(target.getEntityItem());
        inventory.save();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("chaoticbastion:basket");
    }
}
