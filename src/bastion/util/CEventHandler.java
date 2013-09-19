package bastion.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import bastion.CContent;
import bastion.blocks.ColorBlock;
import bastion.world.CrystalValues;
import bastion.world.WorldTracker;

public class CEventHandler
{
    @ForgeSubscribe
    public void registerOre(OreRegisterEvent evt)
    {

    }

    @ForgeSubscribe
    public void playerSleep(PlayerSleepInBedEvent event)
    {
        if (event.entityPlayer.worldObj.provider.dimensionId == 0)
        {
            event.result = EnumStatus.OTHER_PROBLEM;

            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false, 0);
            {
                event.entityPlayer.addChatMessage("Spawn point set!");
            }
        }
    }

    /* Chunks */
    @ForgeSubscribe
    public void chunkDataLoad(ChunkDataEvent.Load event)
    {
        Chunk chunk = event.getChunk();
        int worldID = chunk.worldObj.provider.dimensionId;
        ValueCoordTuple coord = new ValueCoordTuple(worldID, chunk.xPosition, chunk.zPosition);
        CrystalValues theft = new CrystalValues("Theft");
        WorldTracker.theftValue.put(coord, theft.loadFromNBT(event.getData()));
        CrystalValues charge = new CrystalValues("Charge");
        WorldTracker.theftValue.put(coord, charge.loadFromNBT(event.getData()));
    }

    @ForgeSubscribe
    public void chunkDataSave(ChunkDataEvent.Save event)
    {
        Chunk chunk = event.getChunk();
        int worldID = chunk.worldObj.provider.dimensionId;
        ValueCoordTuple coord = new ValueCoordTuple(worldID, chunk.xPosition, chunk.zPosition);
        if (WorldTracker.theftValue.containsKey(coord))
        {
            CrystalValues crystal = WorldTracker.theftValue.get(coord);
            crystal.saveToNBT(event.getData());
            if (!event.getChunk().isChunkLoaded)
            {
                WorldTracker.theftValue.remove(coord);
            }
        }

        if (WorldTracker.charge.containsKey(coord))
        {
            CrystalValues crystal = WorldTracker.charge.get(coord);
            crystal.saveToNBT(event.getData());
            if (!event.getChunk().isChunkLoaded)
            {
                WorldTracker.charge.remove(coord);
            }
        }
    }

    @ForgeSubscribe
    public void playerInteract(PlayerInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        //Dyes
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            ItemStack stack = player.getCurrentEquippedItem();
            String type = OreDictionary.getOreName(OreDictionary.getOreID(stack));
            if (type != null)
            {
                type = type.toLowerCase();
                for (int i = 0; i < 16; i++)
                {
                    if (type.equals("dye" + ColorBlock.colorNames[i]))
                    {
                        if (colorStoneBlocks(player.worldObj, event.x, event.y, event.z, i))
                        {
                            if (!player.capabilities.isCreativeMode)
                            {
                                stack.stackSize--;
                                if (stack.stackSize <= 0)
                                    player.destroyCurrentEquippedItem();
                            }

                            player.swingItem();
                            if (!player.worldObj.isRemote)
                            {
                                Block block = Block.stone;
                                player.worldObj.playSoundEffect((double) ((float) event.x + 0.5F), (double) ((float) event.y + 0.5F), (double) ((float) event.z + 0.5F), block.stepSound.getPlaceSound(),
                                        (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public boolean colorStoneBlocks(World world, int x, int y, int z, int inputMeta)
    {
        boolean changed = false;
        int range = 1;
        for (int xPos = -range; xPos <= range; xPos++)
        {
            for (int yPos = -range; yPos <= range; yPos++)
            {
                for (int zPos = -range; zPos <= range; zPos++)
                {
                    int blockID = world.getBlockId(x + xPos, y + yPos, z + zPos);
                    if (blockID == Block.stone.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredStone.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.cobblestone.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredCobble.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.cobblestoneMossy.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredMossCobble.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.stoneBrick.blockID)
                    {
                        changed = true;
                        int meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);

                        if (meta == 0)
                            world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 1)
                            world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredCrackedStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 2)
                            world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredMossStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 3)
                            world.setBlock(x + xPos, y + yPos, z + zPos, CContent.coloredStoneSquareBrick.blockID, inputMeta, 3);

                    }
                    //Road
                    //Fancy brick
                }
            }
        }
        return changed;
    }

    @ForgeSubscribe
    public void pickupItem(EntityItemPickupEvent event)
    {
        ItemStack equip = event.entityPlayer.getCurrentEquippedItem();
        if (equip != null && equip.getItem() instanceof IItemSucker)
        {
            ((IItemSucker)equip.getItem()).addWorldItems(event.item, equip);
            if (event.item.getEntityItem().stackSize <= 0)
                event.setResult(Result.ALLOW);
        }
    }
    
    /* Vanilla AI */

    /*@ForgeSubscribe
    public void spawnEvent (EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityZombie)
        {
            ((EntityLiving)event.entity).tasks.addTask(1, new EntityAIBreakBlock((EntityLiving) event.entity));
        }
    }*/
}
