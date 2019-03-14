package org.pdfsam.task;

import org.sejda.common.ComponentsUtility;
import org.sejda.core.support.io.IOUtils;
import org.sejda.core.support.io.OutputWriters;
import org.sejda.core.support.io.SingleOutputWriter;
import org.sejda.impl.sambox.component.PdfAlternateMixer;
import org.sejda.model.exception.TaskException;
import org.sejda.model.task.BaseTask;
import org.sejda.model.task.TaskExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AlternateMixTask extends BaseTask<AlternateMixSingleInputParameters> {
    private static final Logger LOG = LoggerFactory.getLogger(org.sejda.impl.sambox.AlternateMixTask.class);
    private PdfAlternateMixer mixer = null;
    private SingleOutputWriter outputWriter;

    public AlternateMixTask() {
    }

    public void before(AlternateMixSingleInputParameters parameters, TaskExecutionContext executionContext) throws TaskException {
        super.before(parameters, executionContext);
        this.mixer = new PdfAlternateMixer();
        this.outputWriter = OutputWriters.newSingleOutputWriter(parameters.getExistingOutputPolicy(), executionContext);
    }

    public void execute(AlternateMixSingleInputParameters parameters) throws TaskException {
        LOG.debug("Starting alternate mix of {} input documents", parameters.getInputList().size());
        this.mixer.mix(parameters.getInputList(), this.executionContext());
        this.mixer.setVersionOnPDDocument(parameters.getVersion());
        this.mixer.setCompress(parameters.isCompress());
        File tmpFile = IOUtils.createTemporaryBuffer(parameters.getOutput());
        this.outputWriter.taskOutput(tmpFile);
        LOG.debug("Temporary output set to {}", tmpFile);
        this.mixer.savePDDocument(tmpFile);
        ComponentsUtility.nullSafeCloseQuietly(this.mixer);
        parameters.getOutput().accept(this.outputWriter);
        LOG.debug("Alternate mix of {} files completed", parameters.getInputList().size());
    }

    public void after() {
        ComponentsUtility.nullSafeCloseQuietly(this.mixer);
    }
}
