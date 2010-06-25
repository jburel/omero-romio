/*
 * ome.io.nio.utests.OutOfBoundsUnitTest
 *
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */
package ome.io.nio.utests;

import junit.framework.TestCase;
import ome.io.nio.DimensionsOutOfBoundsException;
import ome.io.nio.OriginalFileMetadataProvider;
import ome.io.nio.PixelBuffer;
import ome.io.nio.PixelsService;
import ome.model.core.Pixels;
import ome.model.enums.PixelsType;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author callan
 * 
 */
public class OutOfBoundsUnitTest extends TestCase {
    private Pixels pixels;

    private PixelBuffer pixelBuffer;
	
	private static final String ROOT = PathUtil.getInstance().getDataFilePath();

    @Override
    @BeforeMethod
    protected void setUp() {
    	OriginalFileMetadataProvider provider =
    		new TestingOriginalFileMetadataProvider();
        pixels = new Pixels();

        pixels.setId(1L);
        pixels.setSizeX(256);
        pixels.setSizeY(256);
        pixels.setSizeZ(64);
        pixels.setSizeC(3);
        pixels.setSizeT(50);

        PixelsType type = new PixelsType();
        pixels.setPixelsType(type); // FIXME

        PixelsService service = new PixelsService(ROOT);
        pixelBuffer = service.getPixelBuffer(pixels, provider, true);
    }

    @Test
    public void testYUpperBounds() {
        try {
            pixelBuffer.getRowOffset(256, 0, 0, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testZUpperBounds() {
        try {
            pixelBuffer.getPlaneOffset(64, 0, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testCUpperBounds() {
        try {
            pixelBuffer.getStackOffset(3, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testTUpperBounds() {
        try {
            pixelBuffer.getTimepointOffset(50);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testYLowerBounds() {
        try {
            pixelBuffer.getRowOffset(-1, 0, 0, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testZLowerBounds() {
        try {
            pixelBuffer.getPlaneOffset(-1, 0, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testCLowerBounds() {
        try {
            pixelBuffer.getStackOffset(-1, 0);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }

    @Test
    public void testTLowerBounds() {
        try {
            pixelBuffer.getTimepointOffset(-1);
        } catch (DimensionsOutOfBoundsException e) {
            return;
        }

        fail("Out of bounds dimension was not caught.");
    }
}
