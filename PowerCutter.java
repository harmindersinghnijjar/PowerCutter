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
            // If the inventory isn't full, find the closest tree and chop it down.
        } else {
            // If action return -1, the player is idle.
            int action = Players.getLocal().getAnimation();
            // If play is idle.
            if (action == -1) {
                // If the player is standing still, return true.
                boolean standStatus = Players.getLocal().isStandingStill();
                log("Standing still: " + standStatus);
                // If the player is standing still i.e., not moving towards another tree, find the closest tree and chop it down.
                if (standStatus) {
                    tree = GameObjects.closest("Tree");
                    tree.interact("Chop down");
                }

            }


        }
        return 1000;
    }
}