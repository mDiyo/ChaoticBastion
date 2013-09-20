package bastion.util;

public class CoordITuple
{
    public final int x;
    public final int y;
    public final int z;

    public CoordITuple(int posX, int posY, int posZ)
    {
        x = posX;
        y = posY;
        z = posZ;
    }
    
    public CoordITuple(double posX, double posY, double posZ)
    {
        x = (int) Math.floor(posX);
        y = (int) Math.floor(posY);
        z = (int) Math.floor(posZ);
    }

    public boolean equalCoords (int posX, int posY, int posZ)
    {
        if (this.x == posX && this.y == posY && this.z == posZ)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (getClass() == obj.getClass())
        {
            CoordITuple coord = (CoordITuple) obj;
            if (this.x == coord.x && this.y == coord.y && this.z == coord.z)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode ()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    public String toString ()
    {
        return "X: " + x + ", Y: " + y + ", Z: " + z;
    }
}
