package com.science.earth.biogeochemistry.freshwaters.pandora.general.calculations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.science.earth.biogeochemistry.freshwaters.pandora.general.objects.PandoraDifferentialEquations;
import com.science.earth.biogeochemistry.freshwaters.pandora.general.objects.PandoraTimestep;
import com.science.earth.biogeochemistry.freshwaters.pandora.general.services.calculation.interfaces.ReactionCalculationService;
import com.science.earth.biogeochemistry.freshwaters.pandora.general.services.calculation.interfaces.TransportCalculationService;

class PandoraDifferentialEquationsTest {

    @Mock
    PandoraTimestep pandoraTimestep;

    @Mock
    ReactionCalculationService reactionCalculationService;

    @Mock
    TransportCalculationService transportCalculationService;

    PandoraDifferentialEquations pandoraDifferentialEquations;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.openMocks(this);
	pandoraDifferentialEquations = PandoraDifferentialEquations.builder().pandoraTimestep(pandoraTimestep)
		.transportCalculationService(transportCalculationService)
		.reactionCalculationService(reactionCalculationService).build();
    }

    @Test
    void testGetDimension() {
	// given
	when(pandoraTimestep.getDimension()).thenReturn(2);

	// when
	int dimension = pandoraDifferentialEquations.getDimension();

	// then
	assertEquals(2, dimension);
    }

    @Test
    void testComputeDerivativesHappy() {
	// given
	when(pandoraTimestep.getDimension()).thenReturn(2);

	when(pandoraTimestep.getTerrestrialSources(0)).thenReturn(1d);
	when(pandoraTimestep.getTerrestrialSources(1)).thenReturn(5d);

	when(pandoraTimestep.getUpstreamSources(0)).thenReturn(2d);
	when(pandoraTimestep.getUpstreamSources(1)).thenReturn(6d);

	double[] mockReactionReturn = { -0.2, 0.2 };
	when(reactionCalculationService.calculateReactions(any(double[].class))).thenReturn(mockReactionReturn);
	double[] mockTransportReturn = { 0.1, 0.1 };
	when(transportCalculationService.calculateTransport(any(double[].class), any(PandoraTimestep.class))).thenReturn(mockTransportReturn);

	double t = 1f;
	double[] y = { 0, 0 };
	double[] dy = { 0, 0 };

	// when
	pandoraDifferentialEquations.computeDerivatives(t, y, dy);

	// then
	assertEquals(2.7d, dy[0], 1e-3);
	assertEquals(11.1d, dy[1], 1e-3);
    }

}
