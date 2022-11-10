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

    GameObject tree = null;

    @Override
    public int onLoop() {
        // Returns the boolean value True if the inventory is full.
        boolean inventoryCheck = isFull();
        // If True drop all items with the id 1511.
        if (inventoryCheck) {
            dropAll(1511);
            // If the inventory isn't full, find the closest Oak tree and chop it down.
        } else {
            // If action return -1, the player is idle.
            int action = Players.getLocal().getAnimation();
            log(action);
            if (action == -1) {
            tree = GameObjects.closest("Tree");
            tree.interact("Chop down");
            }
                
        }

        return 1000;
    }
}
