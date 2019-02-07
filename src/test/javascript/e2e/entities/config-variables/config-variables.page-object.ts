import { element, by, ElementFinder } from 'protractor';

export class ConfigVariablesComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-config-variables div table .btn-danger'));
    title = element.all(by.css('jhi-config-variables div h2#page-heading span')).first();

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

export class ConfigVariablesUpdatePage {
    pageTitle = element(by.id('jhi-config-variables-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    configVarLong1Input = element(by.id('field_configVarLong1'));
    configVarLong2Input = element(by.id('field_configVarLong2'));
    configVarLong3Input = element(by.id('field_configVarLong3'));
    configVarLong4Input = element(by.id('field_configVarLong4'));
    configVarLong5Input = element(by.id('field_configVarLong5'));
    configVarLong6Input = element(by.id('field_configVarLong6'));
    configVarLong7Input = element(by.id('field_configVarLong7'));
    configVarLong8Input = element(by.id('field_configVarLong8'));
    configVarLong9Input = element(by.id('field_configVarLong9'));
    configVarLong10Input = element(by.id('field_configVarLong10'));
    configVarLong11Input = element(by.id('field_configVarLong11'));
    configVarLong12Input = element(by.id('field_configVarLong12'));
    configVarLong13Input = element(by.id('field_configVarLong13'));
    configVarLong14Input = element(by.id('field_configVarLong14'));
    configVarLong15Input = element(by.id('field_configVarLong15'));
    configVarBoolean16Input = element(by.id('field_configVarBoolean16'));
    configVarBoolean17Input = element(by.id('field_configVarBoolean17'));
    configVarBoolean18Input = element(by.id('field_configVarBoolean18'));
    configVarString19Input = element(by.id('field_configVarString19'));
    configVarString20Input = element(by.id('field_configVarString20'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setConfigVarLong1Input(configVarLong1) {
        await this.configVarLong1Input.sendKeys(configVarLong1);
    }

    async getConfigVarLong1Input() {
        return this.configVarLong1Input.getAttribute('value');
    }

    async setConfigVarLong2Input(configVarLong2) {
        await this.configVarLong2Input.sendKeys(configVarLong2);
    }

    async getConfigVarLong2Input() {
        return this.configVarLong2Input.getAttribute('value');
    }

    async setConfigVarLong3Input(configVarLong3) {
        await this.configVarLong3Input.sendKeys(configVarLong3);
    }

    async getConfigVarLong3Input() {
        return this.configVarLong3Input.getAttribute('value');
    }

    async setConfigVarLong4Input(configVarLong4) {
        await this.configVarLong4Input.sendKeys(configVarLong4);
    }

    async getConfigVarLong4Input() {
        return this.configVarLong4Input.getAttribute('value');
    }

    async setConfigVarLong5Input(configVarLong5) {
        await this.configVarLong5Input.sendKeys(configVarLong5);
    }

    async getConfigVarLong5Input() {
        return this.configVarLong5Input.getAttribute('value');
    }

    async setConfigVarLong6Input(configVarLong6) {
        await this.configVarLong6Input.sendKeys(configVarLong6);
    }

    async getConfigVarLong6Input() {
        return this.configVarLong6Input.getAttribute('value');
    }

    async setConfigVarLong7Input(configVarLong7) {
        await this.configVarLong7Input.sendKeys(configVarLong7);
    }

    async getConfigVarLong7Input() {
        return this.configVarLong7Input.getAttribute('value');
    }

    async setConfigVarLong8Input(configVarLong8) {
        await this.configVarLong8Input.sendKeys(configVarLong8);
    }

    async getConfigVarLong8Input() {
        return this.configVarLong8Input.getAttribute('value');
    }

    async setConfigVarLong9Input(configVarLong9) {
        await this.configVarLong9Input.sendKeys(configVarLong9);
    }

    async getConfigVarLong9Input() {
        return this.configVarLong9Input.getAttribute('value');
    }

    async setConfigVarLong10Input(configVarLong10) {
        await this.configVarLong10Input.sendKeys(configVarLong10);
    }

    async getConfigVarLong10Input() {
        return this.configVarLong10Input.getAttribute('value');
    }

    async setConfigVarLong11Input(configVarLong11) {
        await this.configVarLong11Input.sendKeys(configVarLong11);
    }

    async getConfigVarLong11Input() {
        return this.configVarLong11Input.getAttribute('value');
    }

    async setConfigVarLong12Input(configVarLong12) {
        await this.configVarLong12Input.sendKeys(configVarLong12);
    }

    async getConfigVarLong12Input() {
        return this.configVarLong12Input.getAttribute('value');
    }

    async setConfigVarLong13Input(configVarLong13) {
        await this.configVarLong13Input.sendKeys(configVarLong13);
    }

    async getConfigVarLong13Input() {
        return this.configVarLong13Input.getAttribute('value');
    }

    async setConfigVarLong14Input(configVarLong14) {
        await this.configVarLong14Input.sendKeys(configVarLong14);
    }

    async getConfigVarLong14Input() {
        return this.configVarLong14Input.getAttribute('value');
    }

    async setConfigVarLong15Input(configVarLong15) {
        await this.configVarLong15Input.sendKeys(configVarLong15);
    }

    async getConfigVarLong15Input() {
        return this.configVarLong15Input.getAttribute('value');
    }

    getConfigVarBoolean16Input() {
        return this.configVarBoolean16Input;
    }
    getConfigVarBoolean17Input() {
        return this.configVarBoolean17Input;
    }
    getConfigVarBoolean18Input() {
        return this.configVarBoolean18Input;
    }
    async setConfigVarString19Input(configVarString19) {
        await this.configVarString19Input.sendKeys(configVarString19);
    }

    async getConfigVarString19Input() {
        return this.configVarString19Input.getAttribute('value');
    }

    async setConfigVarString20Input(configVarString20) {
        await this.configVarString20Input.sendKeys(configVarString20);
    }

    async getConfigVarString20Input() {
        return this.configVarString20Input.getAttribute('value');
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

export class ConfigVariablesDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-configVariables-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-configVariables'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
