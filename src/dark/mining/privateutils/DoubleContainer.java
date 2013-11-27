package dark.mining.privateutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Archadia
 *
 */
public class DoubleContainer<A, B>
{

    public ArrayList<A> aList;
    public ArrayList<B> bList;

    public void put(A input1, B input2)
    {
        aList.add(input1);
        bList.add(input2);
    }

    public List getAList()
    {
        return aList;
    }

    public List getBList()
    {
        return bList;
    }
}