import * as THREE from 'three';
import { OrbitControls } from 'three/orbitcontrols';
import * as dat from 'dat.gui';

function init() {
    const scene = new THREE.Scene();

    const geometryTorus = new THREE.TorusKnotGeometry(3, 1, 100, 25);
    const materialTorus = new THREE.MeshPhongMaterial({ color: 0xff0000 });
    const torusKnot = new THREE.Mesh(geometryTorus, materialTorus);
    scene.add(torusKnot);

    const wireframeTorus = new THREE.LineSegments(
        new THREE.EdgesGeometry(geometryTorus),
        new THREE.LineBasicMaterial({ color: 0x000000 })
    );
    torusKnot.add(wireframeTorus);

    const camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 1000);
    camera.position.z = 15;
    camera.lookAt(scene.position);
    scene.add(camera);

    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setClearColor(new THREE.Color(0x000000));
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    document.body.appendChild(renderer.domElement);

    const controls = new OrbitControls(camera, renderer.domElement);
    controls.enableDamping = true;
    controls.dampingFactor = 0.25;
    controls.screenSpacePanning = false;
    controls.maxPolarAngle = Math.PI / 2;

    // Adicionando luz ambiente com controle de intensidade
    const ambientLight = new THREE.AmbientLight(0xffffff, 0.5); // Intensidade inicial: 0.5
    scene.add(ambientLight);

    // Adicionando luz direcional com controle de posição e intensidade
    const directionalLight = new THREE.DirectionalLight(0xffffff, 1); // Intensidade inicial: 1
    directionalLight.position.set(-1, 1, 1);
    scene.add(directionalLight);

    // Adicionando sombras
    renderer.shadowMap.enabled = true;
    directionalLight.castShadow = true;

    // Adicionando uma esfera para representar visualmente o sol
    const sunGeometry = new THREE.SphereGeometry(1, 32, 32);
    const sunMaterial = new THREE.MeshStandardMaterial({
        emissive: 0xffff00, // Cor da emissão (amarela)
        emissiveIntensity: 1, // Intensidade da emissão
        metalness: 0.5, // Propriedade metálica
        roughness: 0.2 // Rugosidade da superfície
    });
    const sun = new THREE.Mesh(sunGeometry, sunMaterial);
    scene.add(sun);

    // GUI para controle das luzes
    const gui = new dat.GUI();

    const ambientLightFolder = gui.addFolder('Ambient Light');
    ambientLightFolder.add(ambientLight, 'intensity', 0, 1).name('Intensity');

    const directionalLightFolder = gui.addFolder('Directional Light');
    directionalLightFolder.add(directionalLight, 'intensity', 0, 1).name('Intensity');
    directionalLightFolder.add(directionalLight.position, 'x', -10, 10).name('Position X');
    directionalLightFolder.add(directionalLight.position, 'y', -10, 10).name('Position Y');
    directionalLightFolder.add(directionalLight.position, 'z', -10, 10).name('Position Z');

    renderer.render(scene, camera);

    function animate() {
        requestAnimationFrame(animate);
        controls.update();

        // Atualizar a posição da esfera (sol) para coincidir com a posição da luz direcional
        sun.position.copy(directionalLight.position);

        renderer.render(scene, camera);
    }

    animate();
}

window.onload = init;
