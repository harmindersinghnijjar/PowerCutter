import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import static org.dreambot.api.methods.container.impl.Inventory.dropAll;
import static org.dreambot.api.methods.container.impl.Inventory.isFull;

// Script manifest is required to provide details about your script.
@ScriptManifest(name = "Power Cutter", description = "Chop down, drop.", author = "Harminder Singh Nijjar",  version = 1.0,
        category = Category.WOODCUTTING, image = "")


public class PowerCutter extends AbstractScript {

    GameObject oakTree = null;

    @Override
    public int onLoop() {
        // Returns the boolean value True if the inventory is full.
        boolean inventoryCheck = isFull();
        // If True drop all items with the ids 1511 and 1521.
        if (inventoryCheck) {
            dropAll(1511, 1521);
            // If the inventory isn't full, find the closest Oak tree and chop it down.
        } else {
            oakTree = GameObjects.closest("Oak");
            oakTree.interact("Chop down");
            }


        return 1000;
    }
}
