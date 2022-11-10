// Required classes.
import org.dreambot.api.methods.input.mouse.MouseSetting;
import org.dreambot.api.methods.input.mouse.MouseSettings;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.Character;
import org.dreambot.api.wrappers.interactive.GameObject;
import java.util.concurrent.locks.Condition;
import static org.dreambot.api.methods.container.impl.Inventory.dropAll;
import static org.dreambot.api.methods.container.impl.Inventory.isFull;
import static org.dreambot.api.methods.walking.impl.Walking.toggleRun;

// Script manifest is required to provide details about your script.
@ScriptManifest(name = "Power Cutter", description = "Chop down, drop.", author = "Harminder Singh Nijjar",  version = 0.1.0,
        category = Category.WOODCUTTING, image = "")


public class PowerCutter extends AbstractScript {
    // Define a GameObject tree and initialize it with a null value.
     GameObject tree = null;
    // Enable run.
    boolean runOn = toggleRun();

    @Override
    // onLoop() is required to make your bot run in the DreamBot client.
    public int onLoop() {
        // Returns the boolean value True if the inventory is full.
        boolean inventoryCheck = isFull();
        // If True drop all items with the id 1511.
        if (inventoryCheck) {
            dropAll(1511);
            // If the inventory isn't full, find the closest tree and chop it down.
        } else {
            // If action return -1, the player is idle.
            int action = Players.getLocal().getAnimation();
            // If play is idle.
            if (action == -1) {
                // If the player is standing still, return true.
                boolean standStatus = Players.getLocal().isStandingStill();
                // If the player is standing still i.e., not moving towards another tree, find the closest tree and chop it down.
                if (standStatus) {
                    // Find the closest object with the name Tree.
                    tree = GameObjects.closest("Tree");
                    // Interact with the abject and select action Chop Down.
                    tree.interact("Chop down");
                }

            }


        }
        // Since the function onLoop() is of the int type, it must return an integer.
        return 1000;
    }
}
