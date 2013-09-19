package bastion.inventory;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ReportedException;

public class InventoryItem implements IInventory
{
    ItemStack parent;
    ItemStack[] inventory;

    public InventoryItem(ItemStack stack, int slots)
    {
        parent = stack;
        inventory = new ItemStack[slots];
        if (stack.hasTagCompound())
        {
            readFromNBT(stack.getTagCompound().getCompoundTag("Items"));
        }
        else
        {
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound itemTag = new NBTTagCompound();
            tag.setCompoundTag("Items", itemTag);
            stack.setTagCompound(tag);
        }
    }
    
    public void save()
    {
        writeToNBT(parent.getTagCompound().getCompoundTag("Items"));        
    }

    public void clearInventory ()
    {
        inventory = new ItemStack[inventory.length];
    }

    @Override
    public int getSizeInventory ()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot (int id)
    {
        return inventory[id];
    }

    @Override
    public ItemStack decrStackSize (int id, int j)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing (int id)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents (int id, ItemStack itemstack)
    {
        inventory[id] = itemstack;
    }

    @Override
    public String getInvName ()
    {
        return "";
    }

    @Override
    public boolean isInvNameLocalized ()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit ()
    {
        return 64;
    }

    @Override
    public void onInventoryChanged ()
    {

    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void openChest ()
    {

    }

    @Override
    public void closeChest ()
    {

    }

    @Override
    public boolean isItemValidForSlot (int i, ItemStack itemstack)
    {
        return true;
    }

    /* NBT */
    protected void readFromNBT (NBTTagCompound tags)
    {
        NBTTagList nbttaglist = tags.getTagList("Items");
        inventory = new ItemStack[getSizeInventory()];
        for (int iter = 0; iter < nbttaglist.tagCount(); iter++)
        {
            NBTTagCompound tagList = (NBTTagCompound) nbttaglist.tagAt(iter);
            byte slotID = tagList.getByte("Slot");
            if (slotID >= 0 && slotID < inventory.length)
            {
                inventory[slotID] = ItemStack.loadItemStackFromNBT(tagList);
            }
        }
    }

    protected void writeToNBT (NBTTagCompound tags)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for (int iter = 0; iter < inventory.length; iter++)
        {
            if (inventory[iter] != null)
            {
                NBTTagCompound tagList = new NBTTagCompound();
                tagList.setByte("Slot", (byte) iter);
                inventory[iter].writeToNBT(tagList);
                nbttaglist.appendTag(tagList);
            }
        }

        tags.setTag("Items", nbttaglist);
    }

    /* Inventory Management */

    public boolean addItemStackToInventory (ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return false;
        }
        else
        {
            try
            {
                int i;

                if (itemstack.isItemDamaged())
                {
                    i = this.getFirstEmptyStack();

                    if (i >= 0)
                    {
                        this.inventory[i] = ItemStack.copyItemStack(itemstack);
                        this.inventory[i].animationsToGo = 5;
                        itemstack.stackSize = 0;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    do
                    {
                        i = itemstack.stackSize;
                        itemstack.stackSize = this.storePartialItemStack(itemstack);
                    } while (itemstack.stackSize > 0 && itemstack.stackSize < i);

                    return itemstack.stackSize < i;
                }
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
                crashreportcategory.addCrashSection("Item ID", Integer.valueOf(itemstack.itemID));
                crashreportcategory.addCrashSection("Item data", Integer.valueOf(itemstack.getItemDamage()));
                throw new ReportedException(crashreport);
            }
        }
    }

    public int getFirstEmptyStack () //Equipped?
    {
        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] == null)
            {
                return i;
            }
        }

        return -1;
    }

    private int storePartialItemStack (ItemStack itemstack)
    {
        int i = itemstack.itemID;
        int j = itemstack.stackSize;
        int k;

        if (itemstack.getMaxStackSize() == 1)
        {
            k = this.getFirstEmptyStack();

            if (k < 0)
            {
                return j;
            }
            else
            {
                if (this.inventory[k] == null)
                {
                    this.inventory[k] = ItemStack.copyItemStack(itemstack);
                }

                return 0;
            }
        }
        else
        {
            k = this.storeItemStack(itemstack);

            if (k < 0)
            {
                k = this.getFirstEmptyStack();
            }

            if (k < 0)
            {
                return j;
            }
            else
            {
                if (this.inventory[k] == null)
                {
                    this.inventory[k] = new ItemStack(i, 0, itemstack.getItemDamage());

                    if (itemstack.hasTagCompound())
                    {
                        this.inventory[k].setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }
                }

                int l = j;

                if (j > this.inventory[k].getMaxStackSize() - this.inventory[k].stackSize)
                {
                    l = this.inventory[k].getMaxStackSize() - this.inventory[k].stackSize;
                }

                if (l > this.getInventoryStackLimit() - this.inventory[k].stackSize)
                {
                    l = this.getInventoryStackLimit() - this.inventory[k].stackSize;
                }

                if (l == 0)
                {
                    return j;
                }
                else
                {
                    j -= l;
                    this.inventory[k].stackSize += l;
                    this.inventory[k].animationsToGo = 5;
                    return j;
                }
            }
        }
    }

    private int storeItemStack (ItemStack itemstack)
    {
        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null && this.inventory[i].itemID == itemstack.itemID && this.inventory[i].isStackable() && this.inventory[i].stackSize < this.inventory[i].getMaxStackSize()
                    && this.inventory[i].stackSize < this.getInventoryStackLimit() && (!this.inventory[i].getHasSubtypes() || this.inventory[i].getItemDamage() == itemstack.getItemDamage())
                    && ItemStack.areItemStackTagsEqual(this.inventory[i], itemstack))
            {
                return i;
            }
        }

        return -1;
    }
}
