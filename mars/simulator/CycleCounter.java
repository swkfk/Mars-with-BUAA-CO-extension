package mars.simulator;

/*
Copyright (c) 2024,  swkfk

Developed by swkfk (kai_Ker@buaa.edu.cn)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject
to the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

(MIT license, http://www.opensource.org/licenses/mit-license.html)
 */

import mars.Globals;

/**
 * Used to count the real cycles of the simulator. The cycles of different instructions may be different.
 *
 * @author swkfk
 * @version September 2024
 */
public class CycleCounter {
    public enum InstructionType {
        DIV(), MUL(), GOTO(), MEM(), OTHER();

        private float cycles;
        private int count;

        InstructionType() {
            this.cycles = 1;
            this.count = 0;
        }
    }

    private void setCycle(InstructionType type, float cycles) {
        type.cycles = cycles;
    }

    public CycleCounter() {
        if (!Globals.getSettings().getCountCycles()) {
            return;
        }
        String[] weights = Globals.getSettings().getCyclesWeight().split(":");
        try {
            setCycle(InstructionType.DIV, Float.parseFloat(weights[0]));
            setCycle(InstructionType.MUL, Float.parseFloat(weights[1]));
            setCycle(InstructionType.GOTO, Float.parseFloat(weights[2]));
            setCycle(InstructionType.MEM, Float.parseFloat(weights[3]));
            setCycle(InstructionType.OTHER, Float.parseFloat(weights[4]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Error: Invalid cycles weight setting.");
            System.exit(1);
        }
    }
}
