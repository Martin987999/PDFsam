package org.pdfsam.task;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.sejda.model.input.PdfMixInput;
import org.sejda.model.output.SingleTaskOutput;
import org.sejda.model.parameter.base.AbstractPdfOutputParameters;
import org.sejda.model.parameter.base.SingleOutputTaskParameters;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlternateMixSingleInputParameters extends AbstractPdfOutputParameters implements SingleOutputTaskParameters {
    @Valid
    @NotNull
    private SingleTaskOutput output;

    private List<PdfMixInput> inputList = new ArrayList();

    public AlternateMixSingleInputParameters() {
    }

    public SingleTaskOutput getOutput() {
        return this.output;
    }

    public void setOutput(SingleTaskOutput output) {
        this.output = output;
    }

    public List<PdfMixInput> getInputList() {
        return Collections.unmodifiableList(this.inputList);
    }

    public void addInput(PdfMixInput input) {
        this.inputList.add(input);
    }

    public int hashCode() {
        return (new HashCodeBuilder()).appendSuper(super.hashCode()).append(this.inputList).append(this.output).toHashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof AlternateMixSingleInputParameters)) {
            return false;
        } else {
            AlternateMixSingleInputParameters parameter = (AlternateMixSingleInputParameters) other;
            return (new EqualsBuilder()).appendSuper(super.equals(other)).append(this.inputList, parameter.inputList).append(this.output, parameter.getOutput()).isEquals();
        }
    }
}
