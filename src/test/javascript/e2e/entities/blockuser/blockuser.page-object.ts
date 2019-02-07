import { element, by, ElementFinder } from 'protractor';

export class BlockuserComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-blockuser div table .btn-danger'));
    title = element.all(by.css('jhi-blockuser div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class BlockuserUpdatePage {
    pageTitle = element(by.id('jhi-blockuser-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    blockeduserSelect = element(by.id('field_blockeduser'));
    blockinguserSelect = element(by.id('field_blockinguser'));
    cblockeduserSelect = element(by.id('field_cblockeduser'));
    cblockinguserSelect = element(by.id('field_cblockinguser'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async blockeduserSelectLastOption() {
        await this.blockeduserSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async blockeduserSelectOption(option) {
        await this.blockeduserSelect.sendKeys(option);
    }

    getBlockeduserSelect(): ElementFinder {
        return this.blockeduserSelect;
    }

    async getBlockeduserSelectedOption() {
        return this.blockeduserSelect.element(by.css('option:checked')).getText();
    }

    async blockinguserSelectLastOption() {
        await this.blockinguserSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async blockinguserSelectOption(option) {
        await this.blockinguserSelect.sendKeys(option);
    }

    getBlockinguserSelect(): ElementFinder {
        return this.blockinguserSelect;
    }

    async getBlockinguserSelectedOption() {
        return this.blockinguserSelect.element(by.css('option:checked')).getText();
    }

    async cblockeduserSelectLastOption() {
        await this.cblockeduserSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cblockeduserSelectOption(option) {
        await this.cblockeduserSelect.sendKeys(option);
    }

    getCblockeduserSelect(): ElementFinder {
        return this.cblockeduserSelect;
    }

    async getCblockeduserSelectedOption() {
        return this.cblockeduserSelect.element(by.css('option:checked')).getText();
    }

    async cblockinguserSelectLastOption() {
        await this.cblockinguserSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cblockinguserSelectOption(option) {
        await this.cblockinguserSelect.sendKeys(option);
    }

    getCblockinguserSelect(): ElementFinder {
        return this.cblockinguserSelect;
    }

    async getCblockinguserSelectedOption() {
        return this.cblockinguserSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class BlockuserDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-blockuser-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-blockuser'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
