/**
 */
package mdsebook.printers.T5.impl;

import mdsebook.printers.T5.Printer;
import mdsebook.printers.T5.PrinterPool;
import mdsebook.printers.T5.T5Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Printer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T5.impl.PrinterImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link mdsebook.printers.T5.impl.PrinterImpl#isColor <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrinterImpl extends MinimalEObjectImpl.Container implements Printer {
	/**
	 * The default value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected boolean color = COLOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrinterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return T5Package.Literals.PRINTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrinterPool getPool() {
		if (eContainerFeatureID() != T5Package.PRINTER__POOL) return null;
		return (PrinterPool)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPool(PrinterPool newPool, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPool, T5Package.PRINTER__POOL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPool(PrinterPool newPool) {
		if (newPool != eInternalContainer() || (eContainerFeatureID() != T5Package.PRINTER__POOL && newPool != null)) {
			if (EcoreUtil.isAncestor(this, newPool))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPool != null)
				msgs = ((InternalEObject)newPool).eInverseAdd(this, T5Package.PRINTER_POOL__PRINTER, PrinterPool.class, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T5Package.PRINTER__POOL, newPool, newPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(boolean newColor) {
		boolean oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T5Package.PRINTER__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPool((PrinterPool)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				return basicSetPool(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case T5Package.PRINTER__POOL:
				return eInternalContainer().eInverseRemove(this, T5Package.PRINTER_POOL__PRINTER, PrinterPool.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				return getPool();
			case T5Package.PRINTER__COLOR:
				return isColor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				setPool((PrinterPool)newValue);
				return;
			case T5Package.PRINTER__COLOR:
				setColor((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				setPool((PrinterPool)null);
				return;
			case T5Package.PRINTER__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case T5Package.PRINTER__POOL:
				return getPool() != null;
			case T5Package.PRINTER__COLOR:
				return color != COLOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (color: ");
		result.append(color);
		result.append(')');
		return result.toString();
	}

} //PrinterImpl
