package org.hccp.instrument.report.graph.canvas;

import org.hccp.instrument.report.graph.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanvasUtilityTest {
    @Test
    public void testAlphaCalculation() {


        assertEquals(0.5f, CanvasUtility.getAlphaForNode(50, 100), 0);
        assertEquals(0.25f, CanvasUtility.getAlphaForNode(12, 48), 0);
        assertEquals(0.75f, CanvasUtility.getAlphaForNode(36, 48), 0);
        
    }
}
