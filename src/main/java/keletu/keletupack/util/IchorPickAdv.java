package keletu.keletupack.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class IchorPickAdv {
    public void IchorPickAdv(ItemStack item)
    {
        NBTTagCompound nbtTagCompound = item.getTagCompound();

        if (nbtTagCompound == null)
        {
            nbtTagCompound = new NBTTagCompound();
            item.setTagCompound(nbtTagCompound);
        }

        nbtTagCompound.setInteger("awaken", (nbtTagCompound.getInteger("awaken") + 1) % 4);
    }
}
